package com.example.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ThemeMode
import com.example.ui.components.PhonxTopBar
import com.example.ui.screens.GeneratorScreen
import com.example.ui.screens.SavedScreen
import com.example.ui.screens.SettingsScreen
import com.example.viewmodel.MainViewModel

enum class AppTab(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    GENERATOR("Generator", Icons.Filled.AutoAwesome, Icons.Outlined.AutoAwesome, "tab_generator"),
    SAVED("Saved", Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder, "tab_saved"),
    SYSTEM("System", Icons.Filled.Devices, Icons.Outlined.Devices, "tab_system"),
    SETTINGS("Settings", Icons.Filled.Settings, Icons.Outlined.Settings, "tab_settings")
}

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(AppTab.GENERATOR) }
    val snackbarHostState = remember { SnackbarHostState() }

    val generatorState by viewModel.generatorState.collectAsState()
    val savedNames by viewModel.filteredSavedNames.collectAsState()
    val savedSearchQuery by viewModel.savedSearchQuery.collectAsState()
    val savedCount by viewModel.savedCount.collectAsState()
    val currentDeviceProfile by viewModel.currentDeviceProfile.collectAsState()
    val savedDeviceProfiles by viewModel.savedDeviceProfiles.collectAsState()

    val themeMode by viewModel.preferences.themeMode.collectAsState()
    val appAccentColor by viewModel.preferences.appAccentColor.collectAsState()
    val fontOption by viewModel.preferences.fontOption.collectAsState()
    val totalGeneratedCount by viewModel.preferences.totalGeneratedCount.collectAsState()

    // Handle toast/snackbar events
    LaunchedEffect(viewModel) {
        viewModel.toastEvent.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isWideScreen = maxWidth >= 720.dp

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                PhonxTopBar()
            },
            bottomBar = {
                if (!isWideScreen) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 2.dp),
                        shape = RoundedCornerShape(24.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        shadowElevation = 2.dp
                    ) {
                        NavigationBar(
                            containerColor = Color.Transparent,
                            tonalElevation = 0.dp,
                            windowInsets = WindowInsets(0, 0, 0, 0),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                        ) {
                            AppTab.entries.forEach { tab ->
                                val isSelected = selectedTab == tab
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = { selectedTab = tab },
                                    icon = {
                                        if (tab == AppTab.SAVED && savedCount > 0) {
                                            BadgedBox(
                                                badge = {
                                                    Badge(
                                                        containerColor = MaterialTheme.colorScheme.primary,
                                                        contentColor = MaterialTheme.colorScheme.onPrimary
                                                    ) {
                                                        Text(if (savedCount > 99) "99+" else savedCount.toString())
                                                    }
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                                    contentDescription = tab.title,
                                                    modifier = Modifier.size(22.dp)
                                                )
                                            }
                                        } else {
                                            Icon(
                                                imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                                contentDescription = tab.title,
                                                modifier = Modifier.size(22.dp)
                                            )
                                        }
                                    },
                                    label = {
                                        Text(
                                            text = tab.title.uppercase(),
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                                                letterSpacing = 0.8.sp,
                                                fontSize = 10.sp
                                            )
                                        )
                                    },
                                    modifier = Modifier.testTag(tab.testTag),
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = MaterialTheme.colorScheme.primary,
                                        selectedTextColor = MaterialTheme.colorScheme.primary,
                                        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }
                        }
                    }
                }
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { paddingValues ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isWideScreen) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surface
                    ) {
                        AppTab.entries.forEach { tab ->
                            val isSelected = selectedTab == tab
                            NavigationRailItem(
                                selected = isSelected,
                                onClick = { selectedTab = tab },
                                icon = {
                                    if (tab == AppTab.SAVED && savedCount > 0) {
                                        BadgedBox(
                                            badge = {
                                                Badge(
                                                    containerColor = MaterialTheme.colorScheme.primary,
                                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                                ) {
                                                    Text(if (savedCount > 99) "99+" else savedCount.toString())
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                                contentDescription = tab.title
                                            )
                                        }
                                    } else {
                                        Icon(
                                            imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                            contentDescription = tab.title
                                        )
                                    }
                                },
                                label = { Text(tab.title) },
                                modifier = Modifier.testTag(tab.testTag),
                                colors = NavigationRailItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    AnimatedContent(
                        targetState = selectedTab,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "tab_transition"
                    ) { targetTab ->
                        when (targetTab) {
                            AppTab.GENERATOR -> GeneratorScreen(
                                state = generatorState,
                                onGenderSelected = { viewModel.setGender(it) },
                                onStyleSelected = { viewModel.setStyle(it) },
                                onCountrySelected = { viewModel.setCountry(it) },
                                onQuantitySelected = { viewModel.setQuantity(it) },
                                onNoRepeatToggled = { viewModel.setNoRepeat(it) },
                                onFontSelected = { viewModel.setFont(it) },
                                onGenerate = { viewModel.generateNames() },
                                onCopyName = { viewModel.copyNameToClipboard(it) },
                                onToggleSaveName = { viewModel.toggleSaveName(it) },
                                onCopyAll = { viewModel.copyAllVisibleNames(it) },
                                onSaveAll = { viewModel.saveAllVisibleNames(it) },
                                onClearResults = { viewModel.clearResults() },
                                onSearchQueryChange = { viewModel.setSearchQuery(it) },
                                onGenderFilterChange = { viewModel.setFilterGender(it) }
                            )

                            AppTab.SAVED -> SavedScreen(
                                savedNames = savedNames,
                                searchQuery = savedSearchQuery,
                                onSearchQueryChange = { viewModel.setSavedSearchQuery(it) },
                                onCopyName = { viewModel.copyNameToClipboard(it) },
                                onRemoveSavedName = { id, name -> viewModel.removeSavedName(id, name) },
                                onCopyAllSaved = { viewModel.copyAllVisibleNames(it) },
                                onClearAllSaved = { viewModel.clearAllSaved() }
                            )

                            AppTab.SYSTEM -> com.example.ui.screens.SystemScreen(
                                currentProfile = currentDeviceProfile,
                                savedProfiles = savedDeviceProfiles,
                                onGenerateRandom = { viewModel.generateRandomDevice() },
                                onGenerateCustom = { brand, model, android, ram, storage, res ->
                                    viewModel.generateCustomDevice(brand, model, android, ram, storage, res)
                                },
                                onSaveProfile = { viewModel.saveCurrentDeviceProfile() },
                                onDeleteProfile = { viewModel.deleteDeviceProfile(it) },
                                onResetProfile = { viewModel.resetDeviceProfile() },
                                onCopyProfile = { viewModel.copyDeviceProfileToClipboard(it) }
                            )

                            AppTab.SETTINGS -> SettingsScreen(
                                themeMode = themeMode,
                                appAccentColor = appAccentColor,
                                selectedFont = fontOption,
                                totalSavedCount = savedCount,
                                totalGeneratedCount = totalGeneratedCount,
                                onThemeModeChanged = { viewModel.setThemeMode(it) },
                                onAppAccentColorChanged = { viewModel.setAppAccentColor(it) },
                                onFontChanged = { viewModel.setFont(it) },
                                onResetAppearance = { viewModel.resetAppearance() }
                            )
                        }
                    }
                }
            }
        }
    }
}

