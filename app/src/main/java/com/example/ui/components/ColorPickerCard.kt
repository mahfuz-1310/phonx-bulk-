package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.calculateContrastColor

@Composable
fun ColorPickerCard(
    title: String,
    subtitle: String,
    currentColorInt: Int,
    defaultColorInt: Int,
    onColorChanged: (Int) -> Unit,
    testTagPrefix: String,
    modifier: Modifier = Modifier
) {
    var hsv by remember(currentColorInt) {
        val hsvArr = FloatArray(3)
        android.graphics.Color.colorToHSV(currentColorInt, hsvArr)
        mutableStateOf(hsvArr)
    }

    var hue by remember(hsv) { mutableFloatStateOf(hsv[0]) }
    var saturation by remember(hsv) { mutableFloatStateOf(hsv[1]) }
    var value by remember(hsv) { mutableFloatStateOf(hsv[2]) }

    var hexText by remember(currentColorInt) {
        mutableStateOf(String.format("%06X", (0xFFFFFF and currentColorInt)))
    }
    var hexError by remember { mutableStateOf<String?>(null) }

    fun updateColorFromHsv(h: Float, s: Float, v: Float) {
        val colorInt = android.graphics.Color.HSVToColor(floatArrayOf(h, s, v))
        hexText = String.format("%06X", (0xFFFFFF and colorInt))
        hexError = null
        onColorChanged(colorInt)
    }

    val currentColor = Color(currentColorInt)
    val contrastColor = calculateContrastColor(currentColor)

    val presetSwatches = listOf(
        0xFF4F46E5.toInt(), // Indigo
        0xFF7C3AED.toInt(), // Violet
        0xFF2563EB.toInt(), // Blue
        0xFF0284C7.toInt(), // Sky
        0xFF059669.toInt(), // Emerald
        0xFF10B981.toInt(), // Mint
        0xFFD97706.toInt(), // Amber
        0xFFE11D48.toInt(), // Rose
        0xFFDC2626.toInt(), // Crimson
        0xFF0F172A.toInt()  // Slate
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("${testTagPrefix}_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
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
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(currentColor)
                            .border(2.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                IconButton(
                    onClick = {
                        onColorChanged(defaultColorInt)
                        val hsvArr = FloatArray(3)
                        android.graphics.Color.colorToHSV(defaultColorInt, hsvArr)
                        hue = hsvArr[0]
                        saturation = hsvArr[1]
                        value = hsvArr[2]
                        hexText = String.format("%06X", (0xFFFFFF and defaultColorInt))
                    },
                    modifier = Modifier.testTag("${testTagPrefix}_reset_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.RestartAlt,
                        contentDescription = "Reset color",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Preset Swatches
            Text(
                text = "Preset Palettes",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                presetSwatches.take(8).forEach { swatch ->
                    val isSelected = swatch == currentColorInt
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color(swatch))
                            .border(
                                width = if (isSelected) 2.5.dp else 1.dp,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                onColorChanged(swatch)
                                val hsvArr = FloatArray(3)
                                android.graphics.Color.colorToHSV(swatch, hsvArr)
                                hue = hsvArr[0]
                                saturation = hsvArr[1]
                                value = hsvArr[2]
                                hexText = String.format("%06X", (0xFFFFFF and swatch))
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selected",
                                tint = calculateContrastColor(Color(swatch)),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Hue Slider
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hue",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${hue.toInt()}°",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Slider(
                value = hue,
                onValueChange = {
                    hue = it
                    updateColorFromHsv(hue, saturation, value)
                },
                valueRange = 0f..360f,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("${testTagPrefix}_hue_slider"),
                colors = SliderDefaults.colors(
                    thumbColor = currentColor,
                    activeTrackColor = currentColor
                )
            )

            // Saturation Slider
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Saturation",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${(saturation * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Slider(
                value = saturation,
                onValueChange = {
                    saturation = it
                    updateColorFromHsv(hue, saturation, value)
                },
                valueRange = 0.1f..1f,
                modifier = Modifier.fillMaxWidth()
            )

            // Value / Brightness Slider
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Brightness",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${(value * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Slider(
                value = value,
                onValueChange = {
                    value = it
                    updateColorFromHsv(hue, saturation, value)
                },
                valueRange = 0.2f..1f,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // HEX input and live preview
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = hexText,
                    onValueChange = { input ->
                        val clean = input.filter { it.isLetterOrDigit() }.take(6).uppercase()
                        hexText = clean
                        if (clean.length == 6) {
                            try {
                                val parsed = android.graphics.Color.parseColor("#$clean")
                                hexError = null
                                onColorChanged(parsed)
                                val hsvArr = FloatArray(3)
                                android.graphics.Color.colorToHSV(parsed, hsvArr)
                                hue = hsvArr[0]
                                saturation = hsvArr[1]
                                value = hsvArr[2]
                            } catch (_: Exception) {
                                hexError = "Invalid HEX"
                            }
                        }
                    },
                    label = { Text("HEX Code") },
                    prefix = { Text("#") },
                    isError = hexError != null,
                    supportingText = { if (hexError != null) Text(hexError!!) },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("${testTagPrefix}_hex_input"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                // Live Preview Card
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = currentColor,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Sample CTA",
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                            color = contrastColor
                        )
                    }
                }
            }
        }
    }
}
