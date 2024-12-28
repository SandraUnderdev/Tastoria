package com.example.tastoria.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastoria.R
import com.example.tastoria.data.database.FavoriteDatabase
import com.example.tastoria.data.remote.RetrofitInstance
import com.example.tastoria.data.repository.RecipeRepo
import com.example.tastoria.databinding.FragmentTastoriaSavedBinding
import com.example.tastoria.viewmodel.RecipeViewModel
import com.example.tastoria.viewmodel.RecipeViewModelFactory


class TastoriaSavedFragment : Fragment() {

    private lateinit var binding: FragmentTastoriaSavedBinding
    private val recipeViewModel: RecipeViewModel by viewModels {
        val favoriteDao = FavoriteDatabase.invoke(requireContext()).getFavoriteDao()
        val recipeRepo = RecipeRepo(RetrofitInstance.ApiClient.apiService, favoriteDao)
        RecipeViewModelFactory(recipeRepo)
    }

    private lateinit var favoriteAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTastoriaSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteAdapter = RecipeAdapter(
            onItemClicked = { recipeId ->
                val action = TastoriaSavedFragmentDirections.actionTastoriaSavedFragmentToRecipeDetailFragment(recipeId)
                findNavController().navigate(action)
            },
            onFavoriteClicked = { recipe ->
                recipeViewModel.toggleFavorite(recipe)
            }
        )

        binding.favoriteRecyclerView.adapter = favoriteAdapter
        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context)

        recipeViewModel.fetchAllFavorites()

        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            favoriteAdapter.setRecipeList(recipes)
        }
    }
}

