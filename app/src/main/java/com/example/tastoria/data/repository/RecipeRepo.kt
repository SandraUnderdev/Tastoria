package com.example.tastoria.data.repository

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
        favoriteDao.insertFavorites(favorite)
    }

    suspend fun removeFromFavorites(recipe: Recipe) {
        val favorite = Favorite(id = recipe.id, title = recipe.title, image = recipe.image)
        favoriteDao.deleteFavorite(favorite)
    }
    suspend fun getAllFavorites(): LiveData<List<Recipe>> {
        return favoriteDao.getAllFavorites().map { favorites ->
            favorites.map {
                Recipe(
                    analyzedInstructions = emptyList(),
                    cheap = false,
                    cookingMinutes = 0,
                    creditsText = null,
                    cuisines = emptyList(),
                    dairyFree = false,
                    diets = emptyList(),
                    dishTypes = emptyList(),
                    extendedIngredients = emptyList(),
                    gaps = "",
                    glutenFree = false,
                    healthScore = 0.0,
                    id = it.id,
                    image = it.image,
                    imageType = "",
                    instructions = "",
                    ketogenic = false,
                    license = "",
                    lowFodmap = false,
                    occasions = emptyList(),
                    preparationMinutes = 0,
                    pricePerServing = 0.0,
                    readyInMinutes = 0,
                    servings = 0,
                    sourceName = "",
                    sourceUrl = "",
                    spoonacularScore = 0.0,
                    spoonacularSourceUrl = "",
                    summary = "",
                    sustainable = false,
                    title = it.title,
                    vegan = false,
                    vegetarian = false,
                    veryHealthy = false,
                    veryPopular = false,
                    weightWatcherSmartPoints = 0,
                    whole30 = false,
                    winePairing = WinePairing()
                )
            }
        }
    }
}
