package com.example.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.model.FontOption
import com.example.model.Gender
import com.example.model.NameStyle
import com.example.model.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("phonx_bulk_prefs", Context.MODE_PRIVATE)

    companion object {
        const val KEY_THEME_MODE = "theme_mode"
        const val KEY_APP_ACCENT_COLOR = "app_accent_color"
        const val KEY_UI_ACCENT_COLOR = "ui_accent_color"
        const val KEY_BUTTON_COLOR = "button_color"
        const val KEY_DEFAULT_FONT = "default_font"
        const val KEY_DEFAULT_QUANTITY = "default_quantity"
        const val KEY_DEFAULT_COUNTRY_ID = "default_country_id"
        const val KEY_DEFAULT_GENDER = "default_gender"
        const val KEY_DEFAULT_STYLE = "default_style"
        const val KEY_NO_REPEAT = "no_repeat"
        const val KEY_TOTAL_GENERATED = "total_generated_count"

        // Default vibrant & professional Indigo theme
        const val DEFAULT_ACCENT_COLOR = 0xFF4F46E5.toInt() // Indigo 600
        const val DEFAULT_BUTTON_COLOR = 0xFF4F46E5.toInt()
    }

    private val _themeMode = MutableStateFlow(loadThemeMode())
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    private val _appAccentColor = MutableStateFlow(loadAppAccentColor())
    val appAccentColor: StateFlow<Int> = _appAccentColor.asStateFlow()

    private val _uiAccentColor = MutableStateFlow(_appAccentColor.value)
    val uiAccentColor: StateFlow<Int> = _uiAccentColor.asStateFlow()

    private val _buttonColor = MutableStateFlow(_appAccentColor.value)
    val buttonColor: StateFlow<Int> = _buttonColor.asStateFlow()

    private val _fontOption = MutableStateFlow(loadFontOption())
    val fontOption: StateFlow<FontOption> = _fontOption.asStateFlow()

    private val _defaultQuantity = MutableStateFlow(prefs.getInt(KEY_DEFAULT_QUANTITY, 20))
    val defaultQuantity: StateFlow<Int> = _defaultQuantity.asStateFlow()

    private val _defaultCountryId = MutableStateFlow(prefs.getString(KEY_DEFAULT_COUNTRY_ID, "BD") ?: "BD")
    val defaultCountryId: StateFlow<String> = _defaultCountryId.asStateFlow()

    private val _defaultGender = MutableStateFlow(loadGender())
    val defaultGender: StateFlow<Gender> = _defaultGender.asStateFlow()

    private val _defaultStyle = MutableStateFlow(loadStyle())
    val defaultStyle: StateFlow<NameStyle> = _defaultStyle.asStateFlow()

    private val _noRepeat = MutableStateFlow(prefs.getBoolean(KEY_NO_REPEAT, true))
    val noRepeat: StateFlow<Boolean> = _noRepeat.asStateFlow()

    private val _totalGeneratedCount = MutableStateFlow(prefs.getInt(KEY_TOTAL_GENERATED, 0))
    val totalGeneratedCount: StateFlow<Int> = _totalGeneratedCount.asStateFlow()

    private fun loadThemeMode(): ThemeMode {
        val name = prefs.getString(KEY_THEME_MODE, ThemeMode.SYSTEM.name)
        return try {
            ThemeMode.valueOf(name ?: ThemeMode.SYSTEM.name)
        } catch (_: Exception) {
            ThemeMode.SYSTEM
        }
    }

    private fun loadAppAccentColor(): Int {
        if (prefs.contains(KEY_APP_ACCENT_COLOR)) {
            return prefs.getInt(KEY_APP_ACCENT_COLOR, DEFAULT_ACCENT_COLOR)
        }
        return prefs.getInt(KEY_UI_ACCENT_COLOR, DEFAULT_ACCENT_COLOR)
    }

    private fun loadUiAccentColor(): Int {
        return loadAppAccentColor()
    }

    private fun loadButtonColor(): Int {
        return loadAppAccentColor()
    }

    private fun loadFontOption(): FontOption {
        val name = prefs.getString(KEY_DEFAULT_FONT, FontOption.DEFAULT.name)
        return try {
            FontOption.valueOf(name ?: FontOption.DEFAULT.name)
        } catch (_: Exception) {
            FontOption.DEFAULT
        }
    }

    private fun loadGender(): Gender {
        val name = prefs.getString(KEY_DEFAULT_GENDER, Gender.MALE.name)
        return try {
            Gender.valueOf(name ?: Gender.MALE.name)
        } catch (_: Exception) {
            Gender.MALE
        }
    }

    private fun loadStyle(): NameStyle {
        val name = prefs.getString(KEY_DEFAULT_STYLE, NameStyle.MODERN.name)
        return try {
            NameStyle.valueOf(name ?: NameStyle.MODERN.name)
        } catch (_: Exception) {
            NameStyle.MODERN
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        prefs.edit().putString(KEY_THEME_MODE, mode.name).apply()
        _themeMode.value = mode
    }

    fun setAppAccentColor(color: Int) {
        prefs.edit()
            .putInt(KEY_APP_ACCENT_COLOR, color)
            .putInt(KEY_UI_ACCENT_COLOR, color)
            .putInt(KEY_BUTTON_COLOR, color)
            .apply()
        _appAccentColor.value = color
        _uiAccentColor.value = color
        _buttonColor.value = color
    }

    fun setUiAccentColor(color: Int) {
        setAppAccentColor(color)
    }

    fun setButtonColor(color: Int) {
        setAppAccentColor(color)
    }

    fun setFontOption(font: FontOption) {
        prefs.edit().putString(KEY_DEFAULT_FONT, font.name).apply()
        _fontOption.value = font
    }

    fun setDefaultQuantity(quantity: Int) {
        prefs.edit().putInt(KEY_DEFAULT_QUANTITY, quantity).apply()
        _defaultQuantity.value = quantity
    }

    fun setDefaultCountryId(countryId: String) {
        prefs.edit().putString(KEY_DEFAULT_COUNTRY_ID, countryId).apply()
        _defaultCountryId.value = countryId
    }

    fun setDefaultGender(gender: Gender) {
        prefs.edit().putString(KEY_DEFAULT_GENDER, gender.name).apply()
        _defaultGender.value = gender
    }

    fun setDefaultStyle(style: NameStyle) {
        prefs.edit().putString(KEY_DEFAULT_STYLE, style.name).apply()
        _defaultStyle.value = style
    }

    fun setNoRepeat(noRepeat: Boolean) {
        prefs.edit().putBoolean(KEY_NO_REPEAT, noRepeat).apply()
        _noRepeat.value = noRepeat
    }

    fun incrementGeneratedCount(count: Int) {
        val newCount = _totalGeneratedCount.value + count
        prefs.edit().putInt(KEY_TOTAL_GENERATED, newCount).apply()
        _totalGeneratedCount.value = newCount
    }

    fun resetAppearance() {
        prefs.edit()
            .putString(KEY_THEME_MODE, ThemeMode.SYSTEM.name)
            .putInt(KEY_APP_ACCENT_COLOR, DEFAULT_ACCENT_COLOR)
            .putInt(KEY_UI_ACCENT_COLOR, DEFAULT_ACCENT_COLOR)
            .putInt(KEY_BUTTON_COLOR, DEFAULT_BUTTON_COLOR)
            .putString(KEY_DEFAULT_FONT, FontOption.DEFAULT.name)
            .apply()

        _themeMode.value = ThemeMode.SYSTEM
        _appAccentColor.value = DEFAULT_ACCENT_COLOR
        _uiAccentColor.value = DEFAULT_ACCENT_COLOR
        _buttonColor.value = DEFAULT_BUTTON_COLOR
        _fontOption.value = FontOption.DEFAULT
    }
}
