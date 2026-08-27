package com.example

import com.example.data.CountryData
import com.example.generator.NameGeneratorEngine
import com.example.model.FontOption
import com.example.model.Gender
import com.example.model.NameStyle
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NameGeneratorEngineTest {

    @Test
    fun testGenerateBatch_StandardQuantity() = runBlocking {
        val bangladesh = CountryData.getById("bd")
        val names = NameGeneratorEngine.generateBatch(
            gender = Gender.MALE,
            style = NameStyle.MODERN,
            country = bangladesh,
            quantity = 20,
            noRepeat = true,
            font = FontOption.DEFAULT
        )

        assertEquals(20, names.size)
        names.forEach { item ->
            assertTrue(item.name.isNotBlank())
            assertTrue(item.name.contains(" ")) // Has first & last name
            assertEquals(Gender.MALE, item.gender)
            assertEquals("BD", item.country.id)
        }
    }

    @Test
    fun testGenerateBatch_NoRepeatEnforced() = runBlocking {
        val usa = CountryData.getById("us")
        val names = NameGeneratorEngine.generateBatch(
            gender = Gender.FEMALE,
            style = NameStyle.MODERN,
            country = usa,
            quantity = 100,
            noRepeat = true,
            font = FontOption.DEFAULT
        )

        assertEquals(100, names.size)
        val distinct = names.map { it.name.lowercase().trim() }.toSet()
        assertEquals(100, distinct.size)
    }

    @Test
    fun testGenerateBatch_LargeQuantity500() = runBlocking {
        val india = CountryData.getById("in")
        val names = NameGeneratorEngine.generateBatch(
            gender = Gender.UNISEX,
            style = NameStyle.CLASSIC,
            country = india,
            quantity = 500,
            noRepeat = false,
            font = FontOption.PLAYFAIR_DISPLAY
        )

        assertEquals(500, names.size)
    }

    @Test
    fun testGenerateBatch_AllStylesProduceValidNames() = runBlocking {
        val uk = CountryData.getById("gb")
        for (style in NameStyle.entries) {
            val names = NameGeneratorEngine.generateBatch(
                gender = Gender.MALE,
                style = style,
                country = uk,
                quantity = 5,
                noRepeat = true,
                font = FontOption.DEFAULT
            )
            assertEquals(5, names.size)
            names.forEach {
                assertTrue("Style $style generated empty name", it.name.isNotBlank())
            }
        }
    }

    @Test
    fun testCountryDataCoverage() {
        assertTrue(CountryData.ALL_COUNTRIES.size >= 40)
        val bd = CountryData.getById("bd")
        assertEquals("Bangladesh", bd.name)
        val jp = CountryData.getById("jp")
        assertEquals("Japan", jp.name)
    }
}
