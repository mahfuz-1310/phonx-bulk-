package com.example.generator

import com.example.data.CountryData
import com.example.model.Country
import com.example.model.FontOption
import com.example.model.Gender
import com.example.model.GeneratedName
import com.example.model.NameStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.random.Random

object NameGeneratorEngine {

    private val sessionGeneratedNames = mutableSetOf<String>()

    fun clearSessionHistory() {
        sessionGeneratedNames.clear()
    }

    suspend fun generateBatch(
        gender: Gender,
        style: NameStyle,
        country: Country,
        quantity: Int,
        noRepeat: Boolean,
        font: FontOption,
        existingBatch: List<GeneratedName> = emptyList()
    ): List<GeneratedName> = withContext(Dispatchers.Default) {
        val pool = CulturalNamePools.getPoolForCountry(country.id)
        val results = ArrayList<GeneratedName>(quantity)

        val seenInCurrentBatch = HashSet<String>()
        if (noRepeat) {
            existingBatch.forEach { seenInCurrentBatch.add(it.name.lowercase().trim()) }
        }

        var attempts = 0
        val maxAttempts = quantity * 20

        while (results.size < quantity && attempts < maxAttempts) {
            attempts++
            val candidateName = buildRealisticName(gender, style, country, pool)
            val normalized = candidateName.lowercase().trim()

            if (noRepeat) {
                if (seenInCurrentBatch.contains(normalized)) {
                    continue
                }
                if (sessionGeneratedNames.contains(normalized) && attempts < maxAttempts / 2) {
                    // Try to pick a fresh name first
                    continue
                }
                seenInCurrentBatch.add(normalized)
                sessionGeneratedNames.add(normalized)
            }

            results.add(
                GeneratedName(
                    id = UUID.randomUUID().toString(),
                    name = candidateName,
                    gender = gender,
                    country = country,
                    style = style,
                    font = font,
                    timestamp = System.currentTimeMillis()
                )
            )
        }

        // If high quantity needed extra names and ran out of strict unique combinations, fill gracefully
        while (results.size < quantity) {
            val fallbackName = buildRealisticName(gender, style, country, pool)
            results.add(
                GeneratedName(
                    id = UUID.randomUUID().toString(),
                    name = fallbackName,
                    gender = gender,
                    country = country,
                    style = style,
                    font = font,
                    timestamp = System.currentTimeMillis()
                )
            )
        }

        results
    }

    private fun buildRealisticName(
        gender: Gender,
        style: NameStyle,
        country: Country,
        pool: CulturalNamePools.CultureData
    ): String {
        val firstNames = when (gender) {
            Gender.MALE -> when (style) {
                NameStyle.CUTE -> if (pool.cuteMale.isNotEmpty()) pool.cuteMale + pool.maleNames else pool.maleNames
                NameStyle.STYLISH -> if (pool.stylishMale.isNotEmpty()) pool.stylishMale + pool.maleNames else pool.maleNames
                NameStyle.UNIQUE -> if (pool.uniqueMale.isNotEmpty()) pool.uniqueMale + pool.maleNames else pool.maleNames
                NameStyle.CLASSIC -> if (pool.classicMale.isNotEmpty()) pool.classicMale + pool.maleNames else pool.maleNames
                NameStyle.ROYAL -> if (pool.royalMale.isNotEmpty()) pool.royalMale + pool.maleNames else pool.maleNames
                else -> pool.maleNames
            }
            Gender.FEMALE -> when (style) {
                NameStyle.CUTE -> if (pool.cuteFemale.isNotEmpty()) pool.cuteFemale + pool.femaleNames else pool.femaleNames
                NameStyle.STYLISH -> if (pool.stylishFemale.isNotEmpty()) pool.stylishFemale + pool.femaleNames else pool.femaleNames
                NameStyle.UNIQUE -> if (pool.uniqueFemale.isNotEmpty()) pool.uniqueFemale + pool.femaleNames else pool.femaleNames
                NameStyle.CLASSIC -> if (pool.classicFemale.isNotEmpty()) pool.classicFemale + pool.femaleNames else pool.femaleNames
                NameStyle.ROYAL -> if (pool.royalFemale.isNotEmpty()) pool.royalFemale + pool.femaleNames else pool.femaleNames
                else -> pool.femaleNames
            }
            Gender.UNISEX -> {
                val combined = pool.unisexNames + pool.maleNames.take(15) + pool.femaleNames.take(15)
                combined.ifEmpty { pool.maleNames + pool.femaleNames }
            }
        }

        val surnames = pool.surnames.ifEmpty { listOf("Smith", "Johnson", "Davis") }
        val randomFirst = firstNames.randomOrNull() ?: "Alex"
        val randomSurname = surnames.randomOrNull() ?: "Morgan"

        return when (style) {
            NameStyle.MODERN -> {
                "$randomFirst $randomSurname"
            }
            NameStyle.CUTE -> {
                // Short friendly format
                "$randomFirst $randomSurname"
            }
            NameStyle.STYLISH -> {
                if (Random.nextInt(10) < 3 && surnames.size > 1) {
                    val secondSurname = surnames.filter { it != randomSurname }.randomOrNull() ?: randomSurname
                    "$randomFirst $randomSurname-$secondSurname"
                } else {
                    "$randomFirst $randomSurname"
                }
            }
            NameStyle.UNIQUE -> {
                if (Random.nextBoolean() && firstNames.size > 2) {
                    val middleInitial = ('A'..'Z').random()
                    "$randomFirst $middleInitial. $randomSurname"
                } else {
                    "$randomFirst $randomSurname"
                }
            }
            NameStyle.CLASSIC -> {
                if (Random.nextInt(10) < 4 && firstNames.size > 3) {
                    val middleName = firstNames.filter { it != randomFirst }.randomOrNull() ?: ""
                    if (middleName.isNotEmpty()) "$randomFirst $middleName $randomSurname" else "$randomFirst $randomSurname"
                } else {
                    "$randomFirst $randomSurname"
                }
            }
            NameStyle.ROYAL -> {
                if (pool.royalPrefixes.isNotEmpty() && Random.nextInt(10) < 5) {
                    val prefix = pool.royalPrefixes.random()
                    "$prefix $randomFirst $randomSurname"
                } else {
                    "$randomFirst $randomSurname III"
                }
            }
            NameStyle.GAMING -> {
                val prefix = pool.gamingPrefixes.randomOrNull()
                val suffix = pool.gamingSuffixes.randomOrNull()
                when (Random.nextInt(4)) {
                    0 -> if (prefix != null) "$prefix $randomFirst" else "$randomFirst $randomSurname"
                    1 -> if (suffix != null) "$randomFirst $suffix" else "$randomFirst $randomSurname"
                    2 -> "$randomFirst '$randomSurname'"
                    else -> "$randomFirst $randomSurname"
                }
            }
            NameStyle.AESTHETIC -> {
                "$randomFirst $randomSurname"
            }
        }
    }
}
