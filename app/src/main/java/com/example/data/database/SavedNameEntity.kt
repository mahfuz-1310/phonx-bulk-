package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_names")
data class SavedNameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val gender: String,
    val countryId: String,
    val countryName: String,
    val style: String,
    val fontName: String,
    val timestamp: Long = System.currentTimeMillis()
)
