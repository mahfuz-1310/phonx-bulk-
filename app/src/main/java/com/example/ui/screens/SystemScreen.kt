package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.DeviceDataStore
import com.example.model.DeviceProfile

@Composable
fun SystemScreen(
    currentProfile: DeviceProfile?,
    savedProfiles: List<DeviceProfile>,
    onGenerateRandom: () -> Unit,
    onGenerateCustom: (String, String, String, String, String, String) -> Unit,
    onSaveProfile: () -> Unit,
    onDeleteProfile: (String) -> Unit,
    onResetProfile: () -> Unit,
    onCopyProfile: (DeviceProfile) -> Unit,
    modifier: Modifier = Modifier
) {
    var showGeneratorDialog by remember { mutableStateOf(false) }

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
                                text = "SYSTEM",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "System tools and device utilities",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Section 2: Fake Device Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("fake_device_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Fake Device",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Create a simulated device profile for testing and UI preview.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { onGenerateRandom() },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("random_device_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Random Device")
                        }

                        OutlinedButton(
                            onClick = { showGeneratorDialog = true },
                            modifier = Modifier
                                .weight(1f)
                                .testTag("create_device_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Outlined.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Create Device")
                        }
                    }
                }
            }
        }

        // Section 3: Current Generated Device Profile Card (if any)
        if (currentProfile != null) {
            item {
                DeviceProfileCard(
                    profile = currentProfile,
                    isCurrent = true,
                    onCopy = { onCopyProfile(currentProfile) },
                    onRegenerate = { onGenerateRandom() },
                    onSave = { onSaveProfile() },
                    onDelete = { onResetProfile() }
                )
            }
        }

        // Section 4: Saved Device Profiles
        if (savedProfiles.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Saved Device Profiles (${savedProfiles.size})",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            items(savedProfiles, key = { it.id }) { profile ->
                DeviceProfileCard(
                    profile = profile,
                    isCurrent = false,
                    onCopy = { onCopyProfile(profile) },
                    onRegenerate = null,
                    onSave = null,
                    onDelete = { onDeleteProfile(profile.id) }
                )
            }
        }
    }

    if (showGeneratorDialog) {
        DeviceGeneratorDialog(
            onDismiss = { showGeneratorDialog = false },
            onGenerateCustom = { brand, model, android, ram, storage, res ->
                onGenerateCustom(brand, model, android, ram, storage, res)
                showGeneratorDialog = false
            }
        )
    }
}

@Composable
fun DeviceProfileCard(
    profile: DeviceProfile,
    isCurrent: Boolean,
    onCopy: () -> Unit,
    onRegenerate: (() -> Unit)?,
    onSave: (() -> Unit)?,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(if (isCurrent) "current_device_card" else "saved_device_card_${profile.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrent) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            1.dp,
            if (isCurrent) MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.PhoneAndroid,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isCurrent) "Active Simulated Profile" else profile.deviceName,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text(
                        text = profile.brand,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                SpecRow(label = "Model", value = profile.model)
                SpecRow(label = "Android", value = profile.androidVersion)
                SpecRow(label = "RAM", value = profile.ram)
                SpecRow(label = "Storage", value = profile.storage)
                SpecRow(label = "Screen", value = profile.screenResolution)
                SpecRow(label = "CPU", value = profile.cpu)
                SpecRow(label = "GPU", value = profile.gpu)
                SpecRow(label = "Device Name", value = profile.deviceName)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onCopy,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("device_copy_btn"),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Outlined.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Copy")
                }

                if (onRegenerate != null) {
                    OutlinedButton(
                        onClick = onRegenerate,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("device_regen_btn"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Regen")
                    }
                }

                if (onSave != null) {
                    Button(
                        onClick = onSave,
                        modifier = Modifier
                            .weight(1f)
                            .testTag("device_save_btn"),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Outlined.BookmarkAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Save")
                    }
                }

                OutlinedButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("device_delete_btn"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Icon(Icons.Outlined.Delete, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(if (isCurrent) "Reset" else "Delete")
                }
            }
        }
    }
}

@Composable
fun SpecRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun DeviceGeneratorDialog(
    onDismiss: () -> Unit,
    onGenerateCustom: (String, String, String, String, String, String) -> Unit
) {
    var selectedBrand by remember { mutableStateOf(DeviceDataStore.brands.first()) }
    val modelsForBrand = remember(selectedBrand) { DeviceDataStore.brandModels[selectedBrand] ?: emptyList() }
    var selectedModelTriple by remember(modelsForBrand) { mutableStateOf(modelsForBrand.firstOrNull() ?: Triple("Generic", "CPU", "GPU")) }
    var selectedAndroid by remember { mutableStateOf(DeviceDataStore.androidVersions.last()) }
    var selectedRam by remember { mutableStateOf(DeviceDataStore.ramOptions[1]) }
    var selectedStorage by remember { mutableStateOf(DeviceDataStore.storageOptions[1]) }
    var selectedResolution by remember { mutableStateOf(DeviceDataStore.resolutionOptions[1]) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Custom Device Generator",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Text("Select Brand:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(DeviceDataStore.brands) { brand ->
                            val isSelected = brand == selectedBrand
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    selectedBrand = brand
                                    val newModels = DeviceDataStore.brandModels[brand] ?: emptyList()
                                    selectedModelTriple = newModels.firstOrNull() ?: Triple("Generic", "CPU", "GPU")
                                },
                                label = { Text(brand) }
                            )
                        }
                    }
                }

                item {
                    Text("Select Model:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(modelsForBrand) { triple ->
                            val isSelected = triple == selectedModelTriple
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedModelTriple = triple },
                                label = { Text(triple.first) }
                            )
                        }
                    }
                }

                item {
                    Text("Android Version:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(DeviceDataStore.androidVersions) { android ->
                            FilterChip(
                                selected = android == selectedAndroid,
                                onClick = { selectedAndroid = android },
                                label = { Text(android) }
                            )
                        }
                    }
                }

                item {
                    Text("RAM:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(DeviceDataStore.ramOptions) { ram ->
                            FilterChip(
                                selected = ram == selectedRam,
                                onClick = { selectedRam = ram },
                                label = { Text(ram) }
                            )
                        }
                    }
                }

                item {
                    Text("Storage:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(DeviceDataStore.storageOptions) { storage ->
                            FilterChip(
                                selected = storage == selectedStorage,
                                onClick = { selectedStorage = storage },
                                label = { Text(storage) }
                            )
                        }
                    }
                }

                item {
                    Text("Screen Resolution:", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        items(DeviceDataStore.resolutionOptions) { res ->
                            FilterChip(
                                selected = res == selectedResolution,
                                onClick = { selectedResolution = res },
                                label = { Text(res) }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onGenerateCustom(
                        selectedBrand,
                        selectedModelTriple.first,
                        selectedAndroid,
                        selectedRam,
                        selectedStorage,
                        selectedResolution
                    )
                },
                modifier = Modifier.testTag("dialog_generate_btn")
            ) {
                Text("Generate Custom")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        shape = RoundedCornerShape(20.dp)
    )
}
