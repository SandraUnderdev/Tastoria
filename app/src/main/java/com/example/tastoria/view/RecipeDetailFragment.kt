package com.example.tastoria.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tastoria.data.database.RecipeDatabase
import com.example.tastoria.data.remote.RetrofitInstance
import com.example.tastoria.data.repository.RecipeRepo
import com.example.tastoria.databinding.FragmentRecipeDetailBinding
import com.example.tastoria.viewmodel.RecipeViewModel
import com.example.tastoria.viewmodel.RecipeViewModelFactory
import com.squareup.picasso.Picasso


class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory(RecipeRepo(RetrofitInstance.ApiClient.apiService, RecipeDatabase.getDatabase(requireContext()).recipeDao()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId = arguments?.getInt("id") ?: return

        recipeViewModel.fetchRecipe(recipeId, "31a9421e99f242a1a134e8c60503c461")
//        val apiKey = BuildConfig.API_KEY
//        recipeViewModel.fetchRecipes("", apiKey)

        recipeViewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            binding.recipeTitle.text = recipe.title

            binding.recipeReadyIn.text = "Ready in ${recipe.readyInMinutes} minutes"
            binding.recipeServings.text = "Servings: ${recipe.servings}"
            binding.recipeStepsTitle.text = "Instructions"
            binding.recipeIngredientTitle.text = "Ingredients"
            binding.recipeIngredients.text = "${recipe.extendedIngredients}"


            Log.d("RecipeDetailFragment", "Image URL: ${recipe.image}")
            Picasso.get().load(recipe.image).into(binding.recipeImage)

            Picasso.get().load(recipe.image).into(binding.recipeImage)

            val steps = recipe.analyzedInstructions
                .flatMap { it.steps }  // Combine all steps
                .joinToString("\n\n") { "Step ${it.number}: ${it.step}" }

            binding.recipeSteps.text = steps

            val ingredient = recipe.extendedIngredients
                .joinToString("\n") { "${it.name}: ${it.amount} ${it.unit}" }
            binding.recipeIngredients.text = ingredient


        }
    }
}
