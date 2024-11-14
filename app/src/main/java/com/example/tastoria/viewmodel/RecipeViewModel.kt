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

    fun fetchRecipes(apiKey: String) {
        viewModelScope.launch {
            try {
                val recipes = repository.getRecipes(apiKey)
                Log.d("RecipeViewModel", "Fetched ${recipes.size} recipes.")
                _recipeList.postValue(recipes)
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Error fetching recipes", e)
            }
        }
    }

    // Fetch a single recipe by its ID from the repository
    fun fetchRecipe(id: Int, apiKey: String) {
        viewModelScope.launch {
            try {
                // Fetching a single recipe by ID
                val recipe = repository.getRecipe(id, apiKey)
                _recipeDetail.postValue(recipe)  // Update the LiveData with the recipe detail
            } catch (e: Exception) {
                // Handle errors and log them
                Log.e("RecipeViewModel", "Error fetching recipe details", e)
            }
        }
    }
}



//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.tastoria.data.model.Recipe
//import com.example.tastoria.data.repository.RecipeRepo
//import kotlinx.coroutines.launch
//
//class RecipeViewModel(private val repository: RecipeRepo) : ViewModel() {
//
//    // LiveData to hold the list of recipes
//    private val _recipeList = MutableLiveData<List<Recipe>>()
//    val recipeList: LiveData<List<Recipe>> get() = _recipeList
//
//    // LiveData to hold the details of a single recipe
//    private val _recipeDetail = MutableLiveData<Recipe>()
//    val recipeDetail: LiveData<Recipe> get() = _recipeDetail
//
//    // Function to fetch a list of recipes
//    fun fetchRecipes(apiKey: String) {
//        viewModelScope.launch {
//            try {
//                // Fetching the recipes using the repository and API key
//                val recipes = repository.getRecipes(apiKey)
//                Log.d("RecipeViewModel", "Fetched ${recipes.size} recipes.")
//                _recipeList.postValue(recipes)  // Update the LiveData with the fetched list of recipes
//            } catch (e: Exception) {
//                // Log the error and handle it
//                Log.e("RecipeViewModel", "Error fetching recipes", e)
//            }
//        }
//    }
//
//    // Function to fetch details for a single recipe by ID
//    fun fetchRecipe(id: Int, apiKey: String) {
//        viewModelScope.launch {
//            try {
//                // Fetch the recipe details using the repository and API key
//                val recipe = repository.getRecipe(id, apiKey)
//                _recipeDetail.postValue(recipe)  // Update the LiveData with the fetched recipe details
//            } catch (e: Exception) {
//                // Log and handle any errors
//                Log.e("RecipeViewModel", "Error fetching recipe details", e)
//            }
//        }
//    }
//}


//class RecipeViewModel(private val repository: RecipeRepo) : ViewModel() {
//
//    private val _recipeList = MutableLiveData<List<Recipe>>()
//    val recipeList: LiveData<List<Recipe>> get() = _recipeList
//
//    private val _recipeDetail = MutableLiveData<Recipe>()
//    val recipeDetail: LiveData<Recipe> get() = _recipeDetail
//
//    fun fetchRecipes(apiKey: String) {
//        viewModelScope.launch {
//            try {
//                val recipes = repository.getRecipes(apiKey)
//                _recipeList.postValue(recipes)
//            } catch (e: Exception) {
//                Log.e("RecipeViewModel", "Error fetching recipes", e)
//            }
//        }
//    }
//
//    fun fetchRecipe(id: Int) {
//        viewModelScope.launch {
//            try {
//                val recipe = repository.getRecipe(id)  // Fetch a single recipe by ID
//                _recipeDetail.postValue(recipe) // Post the recipe detail to LiveData
//            } catch (e: Exception) {
//                // Handle error
//            }
//        }
//    }
//}
