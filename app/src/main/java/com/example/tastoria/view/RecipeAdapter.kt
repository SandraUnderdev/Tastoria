package com.example.tastoria.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastoria.R
import com.example.tastoria.data.model.Recipe
import com.example.tastoria.databinding.ItemRecipeBinding
import com.squareup.picasso.Picasso

class RecipeAdapter(
    private val onItemClicked: (Int) -> Unit,
    private val onFavoriteClicked: (Recipe) -> Unit) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    private var recipeList = mutableListOf<Recipe>()


    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {

            binding.recipeTitle.text = recipe.title
            Picasso.get().load(recipe.image).into(binding.recipeImage)

            if (recipe.isFavorite) {
                binding.favorite.setImageResource(R.drawable.favorite_filled)
            } else {
                binding.favorite.setImageResource(R.drawable.favorite)
            }

            Log.d("RecipeAdapter", "Binding recipe: ${recipe.title}")


            itemView.setOnClickListener {
                onItemClicked(recipe.id)
            }

            binding.favorite.setOnClickListener {
                onFavoriteClicked(recipe)
            }
        }
    }


    fun setRecipeList(recipes: List<Recipe>) {

        this.recipeList = recipes.toMutableList()

        notifyDataSetChanged()
        Log.d("RecipeAdapter", "Updated recipe list, new size: ${recipeList.size}")
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.bind(recipeList[position])
    }


    override fun getItemCount(): Int {
        return recipeList.size
    }
}

