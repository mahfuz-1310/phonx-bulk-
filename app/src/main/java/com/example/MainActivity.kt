package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.ui.MainScreen
import com.example.ui.theme.DynamicPhonxTheme
import com.example.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeMode by viewModel.preferences.themeMode.collectAsState()
            val appAccentColor by viewModel.preferences.appAccentColor.collectAsState()

            DynamicPhonxTheme(
                themeMode = themeMode,
                accentColorInt = appAccentColor,
                buttonColorInt = appAccentColor
            ) {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

