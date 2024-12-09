package com.example.tastoria.viewmodel



import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastoria.data.model.Recipe
import com.example.tastoria.data.repository.RecipeRepo
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepo) : ViewModel() {
    private val _recipeList = MutableLiveData<List<Recipe>>()
    val recipeList: LiveData<List<Recipe>> get() = _recipeList

    private val _recipeDetail = MutableLiveData<Recipe>()
    val recipeDetail: LiveData<Recipe> get() = _recipeDetail

    fun fetchRecipes(query: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val recipes = repository.getRecipes(query, apiKey)
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
                // Fetching a single recipe by ID
                val recipe = repository.getRecipe(id, apiKey)
                _recipeDetail.postValue(recipe)  // Update the LiveData with the recipe detail
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Error fetching recipe details", e)
            }
        }
    }

    fun toggleFavorite(recipe: Recipe) {
        viewModelScope.launch {
            if (recipe.isFavorite) {
                repository.removeFromFavorites(recipe)
            } else {
                repository.addToFavorites(recipe)
            }
            recipe.isFavorite = !recipe.isFavorite
        }
    }

    fun fetchAllFavorites() {
        viewModelScope.launch {
            val favoritesLiveData = repository.getAllFavorites()  // This returns LiveData
            favoritesLiveData.observeForever { favorites ->
                _recipeList.postValue(favorites)  // Update the list with favorite recipes
            }
        }
    }
}
