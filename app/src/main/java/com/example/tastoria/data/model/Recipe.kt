package com.example.tastoria.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

data class Recipe(
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val cookingMinutes: Int,
    val creditsText: String?,
    val cuisines: List<Any>,
    val dairyFree: Boolean,
    val diets: List<Any>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Double,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val ketogenic: Boolean,
    val license: String,
    val lowFodmap: Boolean,
    val occasions: List<Any>,
    val preparationMinutes: Int,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int,
    val whole30: Boolean,
    val winePairing: WinePairing,
    var favorite: Boolean = false
)

data class InstructionStep(
    val number: Int,
    val step: String
)

data class AnalyzedInstruction(
    val name: String,
    val steps: List<InstructionStep>
)