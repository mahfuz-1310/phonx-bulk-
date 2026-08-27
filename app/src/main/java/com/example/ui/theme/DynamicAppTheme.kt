package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.model.ThemeMode

val LocalButtonColor = staticCompositionLocalOf { Color(0xFF4338CA) }
val LocalButtonContentColor = staticCompositionLocalOf { Color.White }

fun calculateContrastColor(backgroundColor: Color): Color {
    // WCAG relative luminance calculation
    val r = backgroundColor.red
    val g = backgroundColor.green
    val b = backgroundColor.blue
    val luminance = 0.299f * r + 0.587f * g + 0.114f * b
    return if (luminance > 0.55f) Color(0xFF0F172A) else Color(0xFFFFFFFF)
}

@Composable
fun DynamicPhonxTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    accentColorInt: Int = 0xFF4F46E5.toInt(),
    buttonColorInt: Int = 0xFF4338CA.toInt(),
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val primaryColor = Color(accentColorInt)
    val onPrimaryColor = calculateContrastColor(primaryColor)

    val customButtonColor = Color(buttonColorInt)
    val customButtonContentColor = calculateContrastColor(customButtonColor)

    val colorScheme: ColorScheme = if (isDark) {
        darkColorScheme(
            primary = primaryColor,
            onPrimary = onPrimaryColor,
            primaryContainer = primaryColor.copy(alpha = 0.22f),
            onPrimaryContainer = if (primaryColor.red + primaryColor.green + primaryColor.blue > 1.5f) primaryColor else Color(0xFFE0E7FF),
            secondary = primaryColor.copy(alpha = 0.85f),
            onSecondary = onPrimaryColor,
            secondaryContainer = Color(0xFF1E293B),
            onSecondaryContainer = Color(0xFFF1F5F9),
            background = Color(0xFF0B0F19),
            onBackground = Color(0xFFF8FAFC),
            surface = Color(0xFF111827),
            onSurface = Color(0xFFF8FAFC),
            surfaceVariant = Color(0xFF1E293B),
            onSurfaceVariant = Color(0xFF94A3B8),
            surfaceContainer = Color(0xFF161F30),
            surfaceContainerHigh = Color(0xFF1E293B),
            surfaceContainerHighest = Color(0xFF283548),
            outline = Color(0xFF334155),
            outlineVariant = Color(0xFF1E293B),
            error = Color(0xFFEF4444),
            onError = Color.White
        )
    } else {
        lightColorScheme(
            primary = primaryColor,
            onPrimary = onPrimaryColor,
            primaryContainer = Color(0xFFEEF2FF),
            onPrimaryContainer = primaryColor,
            secondary = primaryColor.copy(alpha = 0.85f),
            onSecondary = onPrimaryColor,
            secondaryContainer = Color(0xFFF1F5F9),
            onSecondaryContainer = Color(0xFF1E293B),
            background = Color(0xFFF8F9FE),
            onBackground = Color(0xFF0F172A),
            surface = Color(0xFFFFFFFF),
            onSurface = Color(0xFF0F172A),
            surfaceVariant = Color(0xFFF1F5F9),
            onSurfaceVariant = Color(0xFF64748B),
            surfaceContainer = Color(0xFFF8FAFC),
            surfaceContainerHigh = Color(0xFFF1F5F9),
            surfaceContainerHighest = Color(0xFFE2E8F0),
            outline = Color(0xFFE2E8F0),
            outlineVariant = Color(0xFFF1F5F9),
            error = Color(0xFFDC2626),
            onError = Color.White
        )
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !isDark
        }
    }

    CompositionLocalProvider(
        LocalButtonColor provides customButtonColor,
        LocalButtonContentColor provides customButtonContentColor
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
