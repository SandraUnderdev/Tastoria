package com.example.tastoria.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastoria.data.remote.RetrofitInstance
import com.example.tastoria.data.repository.RecipeRepo
import com.example.tastoria.databinding.FragmentRecipeListBinding
import com.example.tastoria.viewmodel.RecipeViewModel
import com.example.tastoria.viewmodel.RecipeViewModelFactory



class RecipeListFragment : Fragment() {

    private lateinit var binding: FragmentRecipeListBinding
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory(RecipeRepo(RetrofitInstance.ApiClient.apiService))
    }

    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recipeAdapter = RecipeAdapter { recipeId ->

            val action = RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipeId)
            findNavController().navigate(action)
        }

        binding.recipeRecyclerView.adapter = recipeAdapter
        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(context)

        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            // Update the adapter with the new recipe list
            recipeAdapter.setRecipeList(recipes)
        }

        val apiKey = "31a9421e99f242a1a134e8c60503c461"
        recipeViewModel.fetchRecipes(apiKey)
    }
}
