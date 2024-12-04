package com.example.tastoria.data.repository

import com.example.tastoria.data.model.Recipe
import com.example.tastoria.data.remote.ApiService

class RecipeRepo(private val apiService: ApiService) {

    suspend fun getRecipes(query : String, apiKey: String): List<Recipe> {
        val response = apiService.getRecipes(query, apiKey)
        return response.results
    }

    suspend fun getRecipe(id: Int, apiKey: String): Recipe {
        return apiService.getRecipe(id, apiKey)
    }
}