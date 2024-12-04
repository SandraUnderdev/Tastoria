package com.example.tastoria.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastoria.data.database.RecipeDatabase
import com.example.tastoria.data.remote.RetrofitInstance
import com.example.tastoria.data.repository.RecipeRepo
import com.example.tastoria.databinding.FragmentRecipeListBinding
import com.example.tastoria.viewmodel.RecipeViewModel
import com.example.tastoria.viewmodel.RecipeViewModelFactory

class RecipeListFragment : Fragment() {

    private lateinit var binding: FragmentRecipeListBinding
    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory(
            RecipeRepo(
                RetrofitInstance.ApiClient.apiService,
                RecipeDatabase.getDatabase(requireContext()).recipeDao()
            )
        )
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

        (activity as? AppCompatActivity)?.supportActionBar?.hide()

//        recipeAdapter = RecipeAdapter { recipeId ->
//
//            val action =
//                RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(recipeId)
//            findNavController().navigate(action)
//        }

        recipeAdapter = RecipeAdapter(
            onItemClicked = { recipeId ->
                // Navigating to the RecipeDetailFragment
                val action =
                    RecipeListFragmentDirections.actionRecipeListFragmentToRecipeDetailFragment(
                        recipeId
                    )
                findNavController().navigate(action)
            },
            onFavoriteClick = { recipe ->
                if (recipe.favorite) {
                    recipe.favorite = false
                    recipeViewModel.deleteRecipe(recipe)
                } else {
                    recipe.favorite = true
                    recipeViewModel.insertRecipe(recipe)
                }
            }
        )

        binding.recipeRecyclerView.adapter = recipeAdapter
        binding.recipeRecyclerView.layoutManager = LinearLayoutManager(context)



        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.setRecipeList(recipes)
        }

        val apiKey = "31a9421e99f242a1a134e8c60503c461"
        recipeViewModel.fetchRecipes("", apiKey)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                query.let {
                    recipeViewModel.fetchRecipes(it, apiKey)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    recipeViewModel.fetchRecipes(
                        it,
                        apiKey
                    )
                }
                return true
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }
}