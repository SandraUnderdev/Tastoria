package com.example.tastoria.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.tastoria.data.database.Favorite
import com.example.tastoria.data.database.FavoriteDao
import com.example.tastoria.data.model.Recipe
import com.example.tastoria.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepo(private val apiService: ApiService, private val favoriteDao: FavoriteDao) {

    suspend fun getRecipes(apiKey: String): List<Recipe> = withContext(Dispatchers.IO){
        val favourites = favoriteDao.getAllFavouriteIds()
        val response = apiService.getRecipes(apiKey)
        response.results.map { it.copy(isFavorite = favourites.contains(it.id)) }
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
                )
            }
        }
    }
}

