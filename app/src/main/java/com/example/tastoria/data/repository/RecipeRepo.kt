package com.example.tastoria.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.tastoria.data.database.Favorite
import com.example.tastoria.data.database.FavoriteDao
import com.example.tastoria.data.model.Recipe
import com.example.tastoria.data.model.WinePairing
import com.example.tastoria.data.remote.ApiService

class RecipeRepo(private val apiService: ApiService, private val favoriteDao: FavoriteDao) {

    suspend fun getRecipes(query : String, apiKey: String): List<Recipe> {
        val response = apiService.getRecipes(query, apiKey)
        return response.results
    }

    suspend fun getRecipe(id: Int, apiKey: String): Recipe {
        return apiService.getRecipe(id, apiKey)
    }


    suspend fun addToFavorites(recipe: Recipe) {
        val favorite = Favorite(id = recipe.id, title = recipe.title, image = recipe.image)
        favoriteDao.insertFavorite(favorite)
    }

    suspend fun removeFromFavorites(recipe: Recipe) {
        val favorite = Favorite(id = recipe.id, title = recipe.title, image = recipe.image)
        favoriteDao.deleteFavorite(favorite)
    }

    suspend fun getAllFavorites(): LiveData<List<Recipe>> {
        return favoriteDao.getAllFavorites().map { favorites ->
            favorites.map {
                Recipe(
                    id = it.id,
                    image = it.image,
                    title = it.title,
                    isFavorite = it.isFavorite
                )
            }
        }
    }
}
