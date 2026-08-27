package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Country
import com.example.model.FontOption
import com.example.model.Gender
import com.example.model.GeneratedName
import com.example.model.NameStyle
import com.example.ui.components.BulkActionBar
import com.example.ui.components.CountrySelector
import com.example.ui.components.FontStyleSelector
import com.example.ui.components.GenderSelector
import com.example.ui.components.GenerateButton
import com.example.ui.components.NameCard
import com.example.ui.components.NoRepeatToggle
import com.example.ui.components.QuantitySelector
import com.example.ui.components.ResultHeader
import com.example.ui.components.StyleSelector
import com.example.viewmodel.GeneratorUiState

@Composable
fun GeneratorScreen(
    state: GeneratorUiState,
    onGenderSelected: (Gender) -> Unit,
    onStyleSelected: (NameStyle) -> Unit,
    onCountrySelected: (Country) -> Unit,
    onQuantitySelected: (Int) -> Unit,
    onNoRepeatToggled: (Boolean) -> Unit,
    onFontSelected: (FontOption) -> Unit,
    onGenerate: () -> Unit,
    onCopyName: (String) -> Unit,
    onToggleSaveName: (GeneratedName) -> Unit,
    onCopyAll: (List<GeneratedName>) -> Unit,
    onSaveAll: (List<GeneratedName>) -> Unit,
    onClearResults: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onGenderFilterChange: (Gender?) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()

    // Scroll to results when new batch is generated
    LaunchedEffect(state.lastGeneratedCount) {
        if (state.lastGeneratedCount > 0) {
            listState.animateScrollToItem(1) // Scroll down to results section
        }
    }

    val visibleNames = remember(state.generatedNames, state.searchQuery, state.filterGender) {
        state.generatedNames.filter { item ->
            val matchesSearch = state.searchQuery.isBlank() ||
                    item.name.contains(state.searchQuery, ignoreCase = true)
            val matchesGender = state.filterGender == null || item.gender == state.filterGender
            matchesSearch && matchesGender
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .testTag("generator_screen"),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Section 1: Generator Controls Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            modifier = Modifier.size(30.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Outlined.Tune,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "GENERATOR CONTROLS",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 0.5.sp
                                ),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Configure identity rules & cultural batch",
                                style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // 1. Gender Selector
                    GenderSelector(
                        selectedGender = state.selectedGender,
                        onGenderSelected = onGenderSelected
                    )

                    // 2. Name Style Selector
                    StyleSelector(
                        selectedStyle = state.selectedStyle,
                        onStyleSelected = onStyleSelected
                    )

                    // 3. Country Selector
                    CountrySelector(
                        selectedCountry = state.selectedCountry,
                        onCountrySelected = onCountrySelected
                    )

                    // 4. Quantity Selector
                    QuantitySelector(
                        selectedQuantity = state.selectedQuantity,
                        onQuantitySelected = onQuantitySelected
                    )

                    // 5. No Repeat Toggle
                    NoRepeatToggle(
                        noRepeat = state.noRepeat,
                        onToggle = onNoRepeatToggled
                    )

                    // 6. Font Style Selector
                    FontStyleSelector(
                        selectedFont = state.selectedFont,
                        onFontSelected = onFontSelected
                    )

                    // 7. Generate Names CTA Button
                    GenerateButton(
                        isGenerating = state.isGenerating,
                        quantity = state.selectedQuantity,
                        onClick = onGenerate
                    )
                }
            }
        }

        // Section 2: Results Area
        if (state.generatedNames.isNotEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    ResultHeader(
                        totalCount = state.generatedNames.size,
                        visibleCount = visibleNames.size,
                        countryName = state.lastGeneratedCountry?.name ?: state.selectedCountry.name,
                        genderLabel = state.lastGeneratedGender?.label ?: state.selectedGender.label,
                        styleLabel = state.lastGeneratedStyle?.label ?: state.selectedStyle.label
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    BulkActionBar(
                        onCopyAll = { onCopyAll(visibleNames) },
                        onSaveAll = { onSaveAll(visibleNames) },
                        onClearResults = onClearResults,
                        searchQuery = state.searchQuery,
                        onSearchQueryChange = onSearchQueryChange,
                        selectedGenderFilter = state.filterGender,
                        onGenderFilterChange = onGenderFilterChange
                    )
                }
            }

            if (visibleNames.isEmpty()) {
                item {
                    EmptySearchState(query = state.searchQuery)
                }
            } else {
                itemsIndexed(
                    items = visibleNames,
                    key = { _, item -> item.id }
                ) { index, item ->
                    NameCard(
                        generatedName = item,
                        index = index,
                        onCopy = onCopyName,
                        onToggleSave = onToggleSaveName,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        } else {
            // Initial Empty State
            item {
                EmptyGeneratorState(
                    onTapGenerate = onGenerate,
                    isGenerating = state.isGenerating
                )
            }
        }
    }
}

@Composable
private fun EmptyGeneratorState(
    onTapGenerate: () -> Unit,
    isGenerating: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Badge,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No names generated yet",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Configure your desired gender, style, country, and quantity above, then tap Generate Names to create a high-quality realistic batch.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun EmptySearchState(query: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.SearchOff,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "No matching names found",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "No generated names match \"$query\"",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
