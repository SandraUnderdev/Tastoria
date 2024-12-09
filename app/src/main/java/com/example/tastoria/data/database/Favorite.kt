package com.example.tastoria.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String,
    var isFavorite: Boolean = true
)
