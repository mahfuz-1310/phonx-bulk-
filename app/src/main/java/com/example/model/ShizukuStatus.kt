package com.example.model

enum class ShizukuStatus {
    NOT_INSTALLED,
    UNSUPPORTED,
    NOT_RUNNING,
    DISCONNECTED,
    PERMISSION_REQUIRED,
    CONNECTED
}

data class DetailedShizukuStatus(
    val binderAvailable: Boolean = false,
    val permissionGranted: Boolean = false,
    val serviceBound: Boolean = false,
    val apiSupported: Boolean = false,
    val isInstalled: Boolean = false
)
