package com.example.tastoria.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tastoria.R
import com.example.tastoria.data.remote.RetrofitInstance
import com.example.tastoria.data.repository.RecipeRepo
import com.example.tastoria.databinding.FragmentRecipeDetailBinding
import com.example.tastoria.viewmodel.RecipeViewModel
import com.example.tastoria.viewmodel.RecipeViewModelFactory
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory(RecipeRepo(RetrofitInstance.ApiClient.apiService))
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

        recipeViewModel.fetchRecipe(recipeId, "your_api_key_here")

        recipeViewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            binding.recipeTitle.text = recipe.title
            binding.recipeInstructions.text = recipe.instructions
            binding.recipeReadyIn.text = "Ready in ${recipe.readyInMinutes} minutes"
            binding.recipeServings.text = "Servings: ${recipe.servings}"


            Log.d("RecipeDetailFragment", "Image URL: ${recipe.image}")


            Picasso.get().load(recipe.image).into(binding.recipeImage, object : Callback {
                override fun onSuccess() {
                    Log.d("RecipeDetailFragment", "Image loaded successfully.")
                }

                override fun onError(e: Exception?) {
                    Log.e("RecipeDetailFragment", "Error loading image.", e)
                }
            })
        }
    }
}
