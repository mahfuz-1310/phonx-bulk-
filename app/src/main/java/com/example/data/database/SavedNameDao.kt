package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedNameDao {
    @Query("SELECT * FROM saved_names ORDER BY timestamp DESC")
    fun getAllSavedNames(): Flow<List<SavedNameEntity>>

    @Query("SELECT * FROM saved_names WHERE name LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    fun searchSavedNames(query: String): Flow<List<SavedNameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedName: SavedNameEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(savedNames: List<SavedNameEntity>)

    @Query("DELETE FROM saved_names WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM saved_names WHERE LOWER(name) = LOWER(:name)")
    suspend fun deleteByName(name: String)

    @Query("DELETE FROM saved_names")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM saved_names")
    fun getSavedCount(): Flow<Int>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_names WHERE LOWER(name) = LOWER(:name))")
    suspend fun isNameSaved(name: String): Boolean
}
