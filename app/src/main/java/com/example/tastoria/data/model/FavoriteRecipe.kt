package com.example.tastoria.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipe(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String,
    var favorite: Boolean = true // Assuming you want to keep track of the favorite status
)
