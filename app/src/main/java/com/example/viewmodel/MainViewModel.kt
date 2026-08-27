package com.example.viewmodel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.CountryData
import com.example.data.database.AppDatabase
import com.example.data.preferences.AppPreferences
import com.example.data.repository.SavedNamesRepository
import com.example.generator.NameGeneratorEngine
import com.example.model.Country
import com.example.model.DeviceDataStore
import com.example.model.DeviceProfile
import com.example.model.FontOption
import com.example.model.Gender
import com.example.model.GeneratedName
import com.example.model.NameStyle
import com.example.model.ShizukuStatus
import com.example.model.ThemeMode
import rikka.shizuku.Shizuku
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class GeneratorUiState(
    val selectedGender: Gender = Gender.MALE,
    val selectedStyle: NameStyle = NameStyle.MODERN,
    val selectedCountry: Country = CountryData.ALL_COUNTRIES.first(),
    val selectedQuantity: Int = 20,
    val noRepeat: Boolean = true,
    val selectedFont: FontOption = FontOption.DEFAULT,
    val isGenerating: Boolean = false,
    val generatedNames: List<GeneratedName> = emptyList(),
    val searchQuery: String = "",
    val filterGender: Gender? = null,
    val filterCountryId: String? = null,
    val filterStyle: NameStyle? = null,
    val lastGeneratedCount: Int = 0,
    val lastGeneratedCountry: Country? = null,
    val lastGeneratedGender: Gender? = null,
    val lastGeneratedStyle: NameStyle? = null,
    val errorMessage: String? = null
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SavedNamesRepository
    val preferences: AppPreferences = AppPreferences(application)

    private val _generatorState = MutableStateFlow(GeneratorUiState())
    val generatorState: StateFlow<GeneratorUiState> = _generatorState.asStateFlow()

    private val _savedSearchQuery = MutableStateFlow("")
    val savedSearchQuery: StateFlow<String> = _savedSearchQuery.asStateFlow()

    private val _currentDeviceProfile = MutableStateFlow<DeviceProfile?>(preferences.loadCurrentDeviceProfile())
    val currentDeviceProfile: StateFlow<DeviceProfile?> = _currentDeviceProfile.asStateFlow()

    private val _savedDeviceProfiles = MutableStateFlow<List<DeviceProfile>>(preferences.loadSavedDeviceProfiles())
    val savedDeviceProfiles: StateFlow<List<DeviceProfile>> = _savedDeviceProfiles.asStateFlow()

    private val _shizukuStatus = MutableStateFlow<ShizukuStatus>(ShizukuStatus.NOT_INSTALLED)
    val shizukuStatus: StateFlow<ShizukuStatus> = _shizukuStatus.asStateFlow()

    private val shizukuPermissionListener = Shizuku.OnRequestPermissionResultListener { requestCode, grantResult ->
        if (requestCode == 1001) {
            checkShizukuStatus()
        }
    }

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent: SharedFlow<String> = _toastEvent.asSharedFlow()

    init {
        val database = AppDatabase.getDatabase(application)
        repository = SavedNamesRepository(database.savedNameDao())

        // Load initial preferences
        _generatorState.value = _generatorState.value.copy(
            selectedGender = preferences.defaultGender.value,
            selectedStyle = preferences.defaultStyle.value,
            selectedCountry = CountryData.getById(preferences.defaultCountryId.value),
            selectedQuantity = preferences.defaultQuantity.value,
            noRepeat = preferences.noRepeat.value,
            selectedFont = preferences.fontOption.value
        )

        try {
            Shizuku.addRequestPermissionResultListener(shizukuPermissionListener)
        } catch (e: Throwable) {}
        checkShizukuStatus()
    }

    override fun onCleared() {
        super.onCleared()
        try {
            Shizuku.removeRequestPermissionResultListener(shizukuPermissionListener)
        } catch (e: Throwable) {}
    }

    val allSavedNames: StateFlow<List<GeneratedName>> = repository.allSavedNames
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val filteredSavedNames: StateFlow<List<GeneratedName>> = combine(
        allSavedNames,
        _savedSearchQuery
    ) { names, query ->
        if (query.isBlank()) {
            names
        } else {
            names.filter { it.name.contains(query, ignoreCase = true) }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val savedCount: StateFlow<Int> = repository.savedCount
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    fun setGender(gender: Gender) {
        _generatorState.value = _generatorState.value.copy(selectedGender = gender)
    }

    fun setStyle(style: NameStyle) {
        _generatorState.value = _generatorState.value.copy(selectedStyle = style)
    }

    fun setCountry(country: Country) {
        _generatorState.value = _generatorState.value.copy(selectedCountry = country)
    }

    fun setQuantity(quantity: Int) {
        _generatorState.value = _generatorState.value.copy(selectedQuantity = quantity)
    }

    fun setNoRepeat(noRepeat: Boolean) {
        _generatorState.value = _generatorState.value.copy(noRepeat = noRepeat)
        preferences.setNoRepeat(noRepeat)
    }

    fun setFont(font: FontOption) {
        _generatorState.value = _generatorState.value.copy(selectedFont = font)
        preferences.setFontOption(font)
    }

    fun setSearchQuery(query: String) {
        _generatorState.value = _generatorState.value.copy(searchQuery = query)
    }

    fun setFilterGender(gender: Gender?) {
        _generatorState.value = _generatorState.value.copy(filterGender = gender)
    }

    fun setFilterStyle(style: NameStyle?) {
        _generatorState.value = _generatorState.value.copy(filterStyle = style)
    }

    fun setSavedSearchQuery(query: String) {
        _savedSearchQuery.value = query
    }

    fun generateNames() {
        val currentState = _generatorState.value
        if (currentState.isGenerating) return

        viewModelScope.launch {
            _generatorState.value = currentState.copy(isGenerating = true, errorMessage = null)
            try {
                val newBatch = NameGeneratorEngine.generateBatch(
                    gender = currentState.selectedGender,
                    style = currentState.selectedStyle,
                    country = currentState.selectedCountry,
                    quantity = currentState.selectedQuantity,
                    noRepeat = currentState.noRepeat,
                    font = currentState.selectedFont,
                    existingBatch = currentState.generatedNames
                )

                // Check saved status for generated names
                val savedSet = allSavedNames.value.map { it.name.lowercase() }.toSet()
                val batchWithSavedStatus = newBatch.map {
                    it.copy(isSaved = savedSet.contains(it.name.lowercase()))
                }

                preferences.incrementGeneratedCount(batchWithSavedStatus.size)

                _generatorState.value = _generatorState.value.copy(
                    isGenerating = false,
                    generatedNames = batchWithSavedStatus,
                    lastGeneratedCount = batchWithSavedStatus.size,
                    lastGeneratedCountry = currentState.selectedCountry,
                    lastGeneratedGender = currentState.selectedGender,
                    lastGeneratedStyle = currentState.selectedStyle
                )
            } catch (e: Exception) {
                _generatorState.value = _generatorState.value.copy(
                    isGenerating = false,
                    errorMessage = "Generation failed. Please try again."
                )
            }
        }
    }

    fun copyNameToClipboard(name: String) {
        val clipboard = getApplication<Application>().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Phonx Name", name)
        clipboard.setPrimaryClip(clip)
        viewModelScope.launch {
            _toastEvent.emit("Copied: $name")
        }
    }

    fun copyAllVisibleNames(names: List<GeneratedName>) {
        if (names.isEmpty()) return
        val text = names.joinToString(separator = "\n") { it.name }
        val clipboard = getApplication<Application>().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Phonx Bulk Names", text)
        clipboard.setPrimaryClip(clip)
        viewModelScope.launch {
            _toastEvent.emit("Copied ${names.size} names")
        }
    }

    fun toggleSaveName(name: GeneratedName) {
        viewModelScope.launch {
            if (name.isSaved) {
                repository.removeByName(name.name)
                updateGeneratedNameSaveStatus(name.name, false)
                _toastEvent.emit("Removed from saved")
            } else {
                repository.saveName(name.copy(isSaved = true))
                updateGeneratedNameSaveStatus(name.name, true)
                _toastEvent.emit("Saved: ${name.name}")
            }
        }
    }

    fun saveAllVisibleNames(names: List<GeneratedName>) {
        if (names.isEmpty()) return
        viewModelScope.launch {
            repository.saveAllNames(names)
            val updated = _generatorState.value.generatedNames.map { it.copy(isSaved = true) }
            _generatorState.value = _generatorState.value.copy(generatedNames = updated)
            _toastEvent.emit("Saved ${names.size} names")
        }
    }

    fun clearResults() {
        _generatorState.value = _generatorState.value.copy(
            generatedNames = emptyList(),
            lastGeneratedCount = 0,
            searchQuery = "",
            filterGender = null,
            filterStyle = null
        )
        NameGeneratorEngine.clearSessionHistory()
        viewModelScope.launch {
            _toastEvent.emit("Results cleared")
        }
    }

    fun removeSavedName(id: String, name: String) {
        viewModelScope.launch {
            id.toLongOrNull()?.let { repository.removeById(it) } ?: repository.removeByName(name)
            updateGeneratedNameSaveStatus(name, false)
            _toastEvent.emit("Removed $name")
        }
    }

    fun clearAllSaved() {
        viewModelScope.launch {
            repository.clearAll()
            val updated = _generatorState.value.generatedNames.map { it.copy(isSaved = false) }
            _generatorState.value = _generatorState.value.copy(generatedNames = updated)
            _toastEvent.emit("All saved names cleared")
        }
    }

    private fun updateGeneratedNameSaveStatus(name: String, isSaved: Boolean) {
        val updated = _generatorState.value.generatedNames.map {
            if (it.name.equals(name, ignoreCase = true)) it.copy(isSaved = isSaved) else it
        }
        _generatorState.value = _generatorState.value.copy(generatedNames = updated)
    }

    // Settings actions
    fun setThemeMode(mode: ThemeMode) {
        preferences.setThemeMode(mode)
    }

    fun setAppAccentColor(colorInt: Int) {
        preferences.setAppAccentColor(colorInt)
    }

    fun setUiAccentColor(colorInt: Int) {
        preferences.setAppAccentColor(colorInt)
    }

    fun setButtonColor(colorInt: Int) {
        preferences.setAppAccentColor(colorInt)
    }

    fun resetAppearance() {
        preferences.resetAppearance()
        _generatorState.value = _generatorState.value.copy(selectedFont = FontOption.DEFAULT)
        viewModelScope.launch {
            _toastEvent.emit("Appearance reset to default")
        }
    }

    fun generateRandomDevice() {
        val profile = DeviceDataStore.generateRandom()
        _currentDeviceProfile.value = profile
        preferences.saveCurrentDeviceProfile(profile)
        viewModelScope.launch {
            _toastEvent.emit("Generated random device profile: ${profile.model}")
        }
    }

    fun generateCustomDevice(brand: String, model: String, androidVersion: String, ram: String, storage: String, resolution: String) {
        val profile = DeviceDataStore.generateCustom(brand, model, androidVersion, ram, storage, resolution)
        _currentDeviceProfile.value = profile
        preferences.saveCurrentDeviceProfile(profile)
        viewModelScope.launch {
            _toastEvent.emit("Generated custom device profile: ${profile.model}")
        }
    }

    fun saveCurrentDeviceProfile() {
        val current = _currentDeviceProfile.value
        if (current != null) {
            val list = _savedDeviceProfiles.value.toMutableList()
            if (!list.any { it.id == current.id }) {
                list.add(0, current)
                _savedDeviceProfiles.value = list
                preferences.saveSavedDeviceProfiles(list)
                viewModelScope.launch {
                    _toastEvent.emit("Device profile saved successfully")
                }
            } else {
                viewModelScope.launch {
                    _toastEvent.emit("Profile already saved")
                }
            }
        }
    }

    fun deleteDeviceProfile(id: String) {
        val list = _savedDeviceProfiles.value.filter { it.id != id }
        _savedDeviceProfiles.value = list
        preferences.saveSavedDeviceProfiles(list)
        viewModelScope.launch {
            _toastEvent.emit("Device profile deleted")
        }
    }

    fun resetDeviceProfile() {
        _currentDeviceProfile.value = null
        preferences.saveCurrentDeviceProfile(null)
        viewModelScope.launch {
            _toastEvent.emit("Device profile reset")
        }
    }

    fun copyDeviceProfileToClipboard(profile: DeviceProfile) {
        val clipboard = getApplication<Application>().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val text = """
            Device Profile:
            Brand: ${profile.brand}
            Model: ${profile.model}
            Android: ${profile.androidVersion}
            RAM: ${profile.ram}
            Storage: ${profile.storage}
            Screen: ${profile.screenResolution}
            CPU: ${profile.cpu}
            GPU: ${profile.gpu}
            Device Name: ${profile.deviceName}
        """.trimIndent()
        val clip = ClipData.newPlainText("Device Profile", text)
        clipboard.setPrimaryClip(clip)
        viewModelScope.launch {
            _toastEvent.emit("Device profile copied to clipboard")
        }
    }

    fun checkShizukuStatus() {
        val context = getApplication<Application>()
        val isInstalled = try {
            context.packageManager.getPackageInfo("moe.shizuku.manager", 0)
            true
        } catch (e: Throwable) {
            false
        }
        if (!isInstalled) {
            _shizukuStatus.value = ShizukuStatus.NOT_INSTALLED
            return
        }

        val isRunning = try {
            Shizuku.pingBinder()
        } catch (e: Throwable) {
            false
        }
        if (!isRunning) {
            _shizukuStatus.value = ShizukuStatus.NOT_RUNNING
            return
        }

        val hasPermission = try {
            if (Shizuku.isPreV11()) {
                false
            } else {
                Shizuku.checkSelfPermission() == android.content.pm.PackageManager.PERMISSION_GRANTED
            }
        } catch (e: Throwable) {
            false
        }

        if (!hasPermission) {
            _shizukuStatus.value = ShizukuStatus.PERMISSION_REQUIRED
        } else {
            _shizukuStatus.value = ShizukuStatus.CONNECTED
        }
    }

    fun requestShizukuPermission() {
        try {
            if (Shizuku.isPreV11()) {
                viewModelScope.launch {
                    _toastEvent.emit("Shizuku version too old")
                }
            } else if (Shizuku.checkSelfPermission() == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                _shizukuStatus.value = ShizukuStatus.CONNECTED
                viewModelScope.launch {
                    _toastEvent.emit("Shizuku permission already granted")
                }
            } else {
                Shizuku.requestPermission(1001)
            }
        } catch (e: Throwable) {
            viewModelScope.launch {
                _toastEvent.emit("Failed to request Shizuku permission: ${e.localizedMessage}")
            }
        }
    }
}
