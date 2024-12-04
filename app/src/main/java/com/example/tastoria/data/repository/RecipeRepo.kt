package com.example.tastoria.data.repository

import com.example.tastoria.data.database.RecipeDao
import com.example.tastoria.data.model.FavoriteRecipe
import com.example.tastoria.data.model.Recipe
import com.example.tastoria.data.remote.ApiService

class RecipeRepo(private val apiService: ApiService,  private val recipeDao: RecipeDao) {

    suspend fun getRecipes(query : String, apiKey: String): List<Recipe> {
        val response = apiService.getRecipes(query, apiKey)
        return response.results
    }

    suspend fun getRecipe(id: Int, apiKey: String): Recipe {
        return apiService.getRecipe(id, apiKey)
    }

    suspend fun insertFavorite(recipe: Recipe) {
        val favoriteRecipe = FavoriteRecipe(
            id = recipe.id,
            title = recipe.title,
            image = recipe.image
        )
        recipeDao.insertRecipe(favoriteRecipe)
    }

    suspend fun updateFavorite(recipe: Recipe) {
        val favoriteRecipe = FavoriteRecipe(
            id = recipe.id,
            title = recipe.title,
            image = recipe.image
        )
        recipeDao.updateRecipe(favoriteRecipe)
    }
    suspend fun getAllFavoritesFromDb(): List<FavoriteRecipe> {
        return recipeDao.getAllRecipes()
    }

    suspend fun deleteFavorite(recipe: Recipe) {
        val favoriteRecipe = FavoriteRecipe(
            id = recipe.id,
            title = recipe.title,
            image = recipe.image
        )
        recipeDao.deleteRecipe(favoriteRecipe)
    }
}