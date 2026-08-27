package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ShizukuStatus

@Composable
fun ShizukuStartupScreen(
    shizukuStatus: ShizukuStatus,
    onCheckShizuku: () -> Unit,
    onRequestPermission: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Automatically request permission if service is running but permission is required
    LaunchedEffect(shizukuStatus) {
        if (shizukuStatus == ShizukuStatus.PERMISSION_REQUIRED) {
            onRequestPermission()
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Hero Icon / Status Visual
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(
                        when (shizukuStatus) {
                            ShizukuStatus.CONNECTED -> MaterialTheme.colorScheme.primaryContainer
                            ShizukuStatus.PERMISSION_REQUIRED -> MaterialTheme.colorScheme.secondaryContainer
                            else -> MaterialTheme.colorScheme.errorContainer
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when (shizukuStatus) {
                        ShizukuStatus.CONNECTED -> Icons.Outlined.CheckCircle
                        ShizukuStatus.PERMISSION_REQUIRED -> Icons.Outlined.Security
                        ShizukuStatus.NOT_RUNNING -> Icons.Outlined.ErrorOutline
                        ShizukuStatus.NOT_INSTALLED -> Icons.Outlined.Download
                    },
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = when (shizukuStatus) {
                        ShizukuStatus.CONNECTED -> MaterialTheme.colorScheme.primary
                        ShizukuStatus.PERMISSION_REQUIRED -> MaterialTheme.colorScheme.secondary
                        else -> MaterialTheme.colorScheme.error
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Shizuku Authorization",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            val statusTitle = when (shizukuStatus) {
                ShizukuStatus.NOT_INSTALLED -> "Shizuku Not Installed"
                ShizukuStatus.NOT_RUNNING -> "Shizuku Service Not Running"
                ShizukuStatus.PERMISSION_REQUIRED -> "Permission Required"
                ShizukuStatus.CONNECTED -> "Shizuku Connected"
            }

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = when (shizukuStatus) {
                    ShizukuStatus.CONNECTED -> MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                    ShizukuStatus.PERMISSION_REQUIRED -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
                    else -> MaterialTheme.colorScheme.error.copy(alpha = 0.15f)
                }
            ) {
                Text(
                    text = statusTitle,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = when (shizukuStatus) {
                        ShizukuStatus.CONNECTED -> MaterialTheme.colorScheme.primary
                        ShizukuStatus.PERMISSION_REQUIRED -> MaterialTheme.colorScheme.secondary
                        else -> MaterialTheme.colorScheme.error
                    },
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            val description = when (shizukuStatus) {
                ShizukuStatus.NOT_INSTALLED -> "Shizuku is not installed on this device. Shizuku allows applications to access system APIs with elevated privileges securely without full root access. Please install Shizuku to proceed."
                ShizukuStatus.NOT_RUNNING -> "Shizuku service is installed but not running. Please open the Shizuku app and start the service via Wireless Debugging (Android 11+) or Root."
                ShizukuStatus.PERMISSION_REQUIRED -> "Shizuku service is running. Triggering the official Shizuku permission request dialog..."
                ShizukuStatus.CONNECTED -> "Shizuku permission successfully granted! Entering application..."
            }

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Action Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                when (shizukuStatus) {
                    ShizukuStatus.NOT_INSTALLED -> {
                        Button(
                            onClick = {
                                try {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/RikkaApps/Shizuku/releases"))
                                    context.startActivity(intent)
                                } catch (e: Exception) {}
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("shizuku_install_btn"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Outlined.Download, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Install Shizuku", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = onCheckShizuku,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("shizuku_check_again_btn"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Check Status Again", fontSize = 16.sp)
                        }
                    }

                    ShizukuStatus.NOT_RUNNING -> {
                        Button(
                            onClick = onCheckShizuku,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("shizuku_check_again_btn"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Check If Service Started", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    ShizukuStatus.PERMISSION_REQUIRED -> {
                        Button(
                            onClick = onRequestPermission,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("shizuku_request_permission_btn"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Outlined.LockOpen, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Request Shizuku Permission", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = onCheckShizuku,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("shizuku_check_again_btn"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Refresh Status", fontSize = 16.sp)
                        }
                    }

                    ShizukuStatus.CONNECTED -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.primary,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Launching App...", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Continue to App button (Skip/Bypass)
                TextButton(
                    onClick = onSkip,
                    modifier = Modifier.testTag("shizuku_skip_btn")
                ) {
                    Text(
                        text = "Continue to App (Without Shizuku)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
