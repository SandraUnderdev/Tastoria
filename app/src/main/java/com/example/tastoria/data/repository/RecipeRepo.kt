package com.example.tastoria.data.repository

import com.example.tastoria.data.model.Recipe
import com.example.tastoria.data.remote.ApiService

class RecipeRepo(private val apiService: ApiService) {

    suspend fun getRecipes(apiKey: String): List<Recipe> {
        val response = apiService.getRecipes(apiKey)
        return response.results
    }

    suspend fun getRecipe(id: Int, apiKey: String): Recipe {
        return apiService.getRecipe(id, apiKey)
    }
}