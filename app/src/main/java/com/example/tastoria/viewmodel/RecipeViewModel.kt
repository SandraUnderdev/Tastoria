package com.example.tastoria.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastoria.data.model.FavoriteRecipe
import com.example.tastoria.data.model.Recipe
import com.example.tastoria.data.repository.RecipeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel( private val repository: RecipeRepo) : ViewModel() {
   // private val recipeDao = RecipeDatabase.getDatabase(application).recipeDao()
    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>> get() = _recipeList

    private val _recipeDetail = MutableLiveData<Recipe>()
    val recipeDetail: LiveData<Recipe> get() = _recipeDetail

    private val _favoriteRecipes = MutableLiveData<List<FavoriteRecipe>>()
    val favoriteRecipes: LiveData<List<FavoriteRecipe>> get() = _favoriteRecipes

    init {
        getAllFavorites() // Get the initial favorites when the ViewModel is created
    }


    fun fetchRecipes(query: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val recipes = repository.getRecipes(query, apiKey)

                val favoriteRecipes = repository.getAllFavoritesFromDb()

                // Mark the recipes as favorite based on the database
                recipes.forEach { recipe ->
                    recipe.favorite = favoriteRecipes.any { it.id == recipe.id }
                }

                Log.d("RecipeViewModel", "Fetched ${recipes.size} recipes.")
                _recipeList.postValue(recipes)
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Error fetching recipes", e)
            }
        }
    }

    fun fetchRecipe(id: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                val recipe = repository.getRecipe(id, apiKey)
                _recipeDetail.postValue(recipe)
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Error fetching recipe details", e)
            }
        }
    }

    fun insertRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
           try {
               repository.insertFavorite(recipe)
           } catch (e: Exception) {
               Log.e("RecipeViewModel", "Error inserting recipe into DB", e)
           }
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateFavorite(recipe)
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Error updating recipe into DB", e)
            }
        }
    }

    fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val favorites =  repository.getAllFavoritesFromDb()
                _favoriteRecipes.postValue(favorites)
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Error getting all recipes from DB", e)
            }
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteFavorite(recipe)
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Error deleting recipe in DB", e)
            }
        }
    }
}