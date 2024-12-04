package com.example.tastoria.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tastoria.R
import com.example.tastoria.databinding.FragmentTastoriaSavedBinding
import com.example.tastoria.viewmodel.RecipeViewModel


class TastoriaSavedFragment : Fragment() {

    private lateinit var binding: FragmentTastoriaSavedBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private val recipeViewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTastoriaSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeAdapter = RecipeAdapter(
            onItemClicked = { recipeId ->
            },
            onFavoriteClick = { recipe ->
                recipe.favorite = !recipe.favorite
                recipeViewModel.insertRecipe(recipe)
            }
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recipeAdapter

        recipeViewModel.favoriteRecipes.observe(viewLifecycleOwner) { favorites ->
            recipeAdapter.setRecipeList(favorites)
        }

        recipeViewModel.getAllFavorites()
    }
}