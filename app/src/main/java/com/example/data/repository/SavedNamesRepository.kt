package com.example.data.repository

import com.example.data.database.SavedNameDao
import com.example.data.database.SavedNameEntity
import com.example.model.Country
import com.example.model.FontOption
import com.example.model.Gender
import com.example.model.GeneratedName
import com.example.model.NameStyle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SavedNamesRepository(private val dao: SavedNameDao) {

    val allSavedNames: Flow<List<GeneratedName>> = dao.getAllSavedNames().map { entities ->
        entities.map { it.toGeneratedName() }
    }

    val savedCount: Flow<Int> = dao.getSavedCount()

    fun searchSaved(query: String): Flow<List<GeneratedName>> {
        return dao.searchSavedNames(query).map { entities ->
            entities.map { it.toGeneratedName() }
        }
    }

    suspend fun saveName(name: GeneratedName) {
        dao.insert(name.toEntity())
    }

    suspend fun saveAllNames(names: List<GeneratedName>) {
        dao.insertAll(names.map { it.toEntity() })
    }

    suspend fun removeById(id: Long) {
        dao.deleteById(id)
    }

    suspend fun removeByName(name: String) {
        dao.deleteByName(name)
    }

    suspend fun clearAll() {
        dao.clearAll()
    }

    suspend fun isSaved(name: String): Boolean {
        return dao.isNameSaved(name)
    }

    private fun SavedNameEntity.toGeneratedName(): GeneratedName {
        return GeneratedName(
            id = this.id.toString(),
            name = this.name,
            gender = try { Gender.valueOf(this.gender) } catch (_: Exception) { Gender.MALE },
            country = Country(this.countryId, this.countryName, this.countryId, "Global"),
            style = try { NameStyle.valueOf(this.style) } catch (_: Exception) { NameStyle.MODERN },
            font = try { FontOption.valueOf(this.fontName) } catch (_: Exception) { FontOption.DEFAULT },
            timestamp = this.timestamp,
            isSaved = true
        )
    }

    private fun GeneratedName.toEntity(): SavedNameEntity {
        return SavedNameEntity(
            id = this.id.toLongOrNull() ?: 0L,
            name = this.name,
            gender = this.gender.name,
            countryId = this.country.id,
            countryName = this.country.name,
            style = this.style.name,
            fontName = this.font.name,
            timestamp = this.timestamp
        )
    }
}
