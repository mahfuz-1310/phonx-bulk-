package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.model.ShizukuStatus
import com.example.ui.MainScreen
import com.example.ui.screens.ShizukuStartupScreen
import com.example.ui.theme.DynamicPhonxTheme
import com.example.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.checkShizukuStatus()

        setContent {
            val themeMode by viewModel.preferences.themeMode.collectAsState()
            val appAccentColor by viewModel.preferences.appAccentColor.collectAsState()
            val shizukuStatus by viewModel.shizukuStatus.collectAsState()
            var bypassShizuku by remember { mutableStateOf(false) }

            DynamicPhonxTheme(
                themeMode = themeMode,
                accentColorInt = appAccentColor,
                buttonColorInt = appAccentColor
            ) {
                if (shizukuStatus == ShizukuStatus.CONNECTED || bypassShizuku) {
                    MainScreen(viewModel = viewModel)
                } else {
                    ShizukuStartupScreen(
                        shizukuStatus = shizukuStatus,
                        onCheckShizuku = { viewModel.checkShizukuStatus() },
                        onRequestPermission = { viewModel.requestShizukuPermission() },
                        onSkip = { bypassShizuku = true }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.checkShizukuStatus()
    }
}
