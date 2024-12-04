package com.example.tastoria.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tastoria.data.model.FavoriteRecipe
import com.example.tastoria.data.model.Recipe

@androidx.room.Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipes")
    suspend fun getAllRecipes(): List<FavoriteRecipe>

    @Update
    suspend fun updateRecipe(recipe: FavoriteRecipe)

    @Delete
    suspend fun deleteRecipe(favoriteRecipe: FavoriteRecipe)
}

