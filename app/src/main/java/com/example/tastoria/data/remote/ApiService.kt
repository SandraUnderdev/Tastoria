package com.example.tastoria.data.remote

import com.example.tastoria.data.model.Recipe
import com.example.tastoria.data.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/recipes/{id}/information")
    suspend fun getRecipe(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): Recipe


    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @Query("query") query:  String,
        @Query("apiKey") apiKey: String
    ): RecipeResponse
}
//https://spoonacular.com/food-api
// https://api.spoonacular.com/recipes/{id}/information
//API Key: 31a9421e99f242a1a134e8c60503c461