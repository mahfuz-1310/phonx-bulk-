package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.DetailedShizukuStatus
import com.example.model.ShizukuStatus

@Composable
fun SystemScreen(
    currentDeviceName: String,
    shizukuStatus: ShizukuStatus = ShizukuStatus.NOT_RUNNING,
    detailedShizukuStatus: DetailedShizukuStatus = DetailedShizukuStatus(),
    shizukuError: String? = null,
    onCheckShizuku: () -> Unit = {},
    onRequestShizukuPermission: () -> Unit = {},
    onLoadDeviceName: () -> Unit = {},
    onApplyDeviceName: (String) -> Unit = {},
    onRestoreOriginalName: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
            if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                onCheckShizuku()
                onLoadDeviceName()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onCheckShizuku()
        onLoadDeviceName()
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("system_screen"),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Section 1: Header / Intro
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Devices,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "DEVICE NAME",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Real system device name editor",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Section 2: Shizuku Status and Device Name
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("device_name_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Device Name",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Change the real Android system device name.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ShizukuStatusCard(
                        status = shizukuStatus,
                        detailedStatus = detailedShizukuStatus,
                        onCheckAgain = {
                            onCheckShizuku()
                            onLoadDeviceName()
                        },
                        onRequestPermission = onRequestShizukuPermission
                    )

                    if (shizukuStatus == ShizukuStatus.CONNECTED) {
                        Spacer(modifier = Modifier.height(24.dp))
                        DeviceNameEditor(
                            currentName = currentDeviceName,
                            shizukuError = shizukuError,
                            onApply = onApplyDeviceName,
                            onRefresh = onLoadDeviceName,
                            onRestore = onRestoreOriginalName
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DeviceNameEditor(
    currentName: String,
    shizukuError: String? = null,
    onApply: (String) -> Unit,
    onRefresh: () -> Unit,
    onRestore: () -> Unit
) {
    var newNameInput by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Current Device Name",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            Text(
                text = currentName.ifEmpty { "Unknown" },
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "New Device Name",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(4.dp))
        
        OutlinedTextField(
            value = newNameInput,
            onValueChange = { newNameInput = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Enter device name") },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onRefresh,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Refresh", maxLines = 1)
            }
            
            Button(
                onClick = { onApply(newNameInput) },
                modifier = Modifier.weight(1.5f),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(vertical = 12.dp),
                enabled = newNameInput.isNotBlank()
            ) {
                Icon(Icons.Outlined.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Apply Name", maxLines = 1)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        TextButton(
            onClick = onRestore,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Outlined.Restore, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Restore Previous Name")
        }

        if (shizukuError != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.5f)),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Debug Output",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = shizukuError,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }
    }
}

@Composable
fun ShizukuStatusCard(
    status: ShizukuStatus,
    detailedStatus: DetailedShizukuStatus,
    onCheckAgain: () -> Unit,
    onRequestPermission: () -> Unit
) {
    val statusColor = when (status) {
        ShizukuStatus.CONNECTED -> MaterialTheme.colorScheme.primary
        ShizukuStatus.PERMISSION_REQUIRED -> MaterialTheme.colorScheme.secondary
        ShizukuStatus.NOT_RUNNING, ShizukuStatus.NOT_INSTALLED, ShizukuStatus.DISCONNECTED -> MaterialTheme.colorScheme.tertiary
        ShizukuStatus.UNSUPPORTED -> MaterialTheme.colorScheme.error
    }

    val statusIcon = when (status) {
        ShizukuStatus.CONNECTED -> Icons.Outlined.CheckCircle
        ShizukuStatus.PERMISSION_REQUIRED -> Icons.Outlined.Lock
        ShizukuStatus.NOT_RUNNING, ShizukuStatus.NOT_INSTALLED, ShizukuStatus.DISCONNECTED -> Icons.Outlined.Warning
        ShizukuStatus.UNSUPPORTED -> Icons.Outlined.ErrorOutline
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("shizuku_status_card"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = statusColor.copy(alpha = 0.05f)
        ),
        border = BorderStroke(
            1.dp,
            statusColor.copy(alpha = 0.4f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = statusIcon,
                        contentDescription = null,
                        tint = statusColor,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Shizuku Status",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                val statusText = when (status) {
                    ShizukuStatus.UNSUPPORTED -> "Unsupported API"
                    ShizukuStatus.NOT_INSTALLED -> "Not Installed"
                    ShizukuStatus.NOT_RUNNING -> "Not Running"
                    ShizukuStatus.DISCONNECTED -> "Disconnected"
                    ShizukuStatus.PERMISSION_REQUIRED -> "Permission Required"
                    ShizukuStatus.CONNECTED -> "Connected"
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = statusColor.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = statusText,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = statusColor,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            val messageText = when (status) {
                ShizukuStatus.UNSUPPORTED -> "The Shizuku API version on this device is not supported."
                ShizukuStatus.NOT_INSTALLED -> "Shizuku is not installed. Please install it to use this feature."
                ShizukuStatus.NOT_RUNNING -> "Shizuku is not currently available."
                ShizukuStatus.DISCONNECTED -> "Shizuku binder disconnected."
                ShizukuStatus.PERMISSION_REQUIRED -> "Shizuku is running, but this app does not have permission."
                ShizukuStatus.CONNECTED -> "Shizuku permission is granted for this app."
            }

            Text(
                text = messageText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))
            
            // Detailed Real-time Status Indicators
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatusIndicator(
                    label = "Binder",
                    isActive = detailedStatus.binderAvailable,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                StatusIndicator(
                    label = "Permission",
                    isActive = detailedStatus.permissionGranted,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                StatusIndicator(
                    label = "Connection",
                    isActive = detailedStatus.serviceBound,
                    modifier = Modifier.weight(1f)
                )
            }

            if (status != ShizukuStatus.CONNECTED) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (status == ShizukuStatus.PERMISSION_REQUIRED) {
                        Button(
                            onClick = onRequestPermission,
                            modifier = Modifier.testTag("shizuku_request_permission_btn"),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = statusColor)
                        ) {
                            Icon(Icons.Outlined.LockOpen, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Grant Permission")
                        }
                    } else {
                        OutlinedButton(
                            onClick = onCheckAgain,
                            modifier = Modifier.testTag("shizuku_check_again_btn"),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Refresh Status")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatusIndicator(
    label: String,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val color = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
    val icon = if (isActive) Icons.Outlined.CheckCircle else Icons.Outlined.RadioButtonUnchecked
    
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = color.copy(alpha = 0.05f),
        border = BorderStroke(1.dp, color.copy(alpha = 0.2f))
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = color
            )
        }
    }
}
