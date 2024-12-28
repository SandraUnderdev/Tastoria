package com.example.tastoria.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


data class Recipe(
    val analyzedInstructions: List<AnalyzedInstruction>? = null,
    val cheap: Boolean? = null,
    val cookingMinutes: Int? = null,
    val creditsText: String? = null,
    val cuisines: List<Any>? = null,
    val dairyFree: Boolean? = null,
    val diets: List<Any>? = null,
    val dishTypes: List<String>? = null,
    val extendedIngredients: List<ExtendedIngredient>? = null,
    val gaps: String? = null,
    val glutenFree: Boolean? = null,
    val healthScore: Double? = null,
    val id: Int,
    val image: String,
    val imageType: String? = null,
    val instructions: String? = null,
    val ketogenic: Boolean? = null,
    val license: String? = null,
    val lowFodmap: Boolean? = null,
    val occasions: List<Any>? = null,
    val preparationMinutes: Int? = null,
    val pricePerServing: Double? = null,
    val readyInMinutes: Int? = null,
    val servings: Int? = null,
    val sourceName: String? = null,
    val sourceUrl: String? = null,
    val spoonacularScore: Double? = null,
    val spoonacularSourceUrl: String? = null,
    val summary: String? = null,
    val sustainable: Boolean? = null,
    val title: String,
    val vegan: Boolean? = null,
    val vegetarian: Boolean? = null,
    val veryHealthy: Boolean? = null,
    val veryPopular: Boolean? = null,
    val weightWatcherSmartPoints: Int? = null,
    val whole30: Boolean? = null,
    val winePairing: WinePairing? = null,
    var isFavorite: Boolean = false
)

data class InstructionStep(
    val number: Int,
    val step: String
)

data class AnalyzedInstruction(
    val name: String,
    val steps: List<InstructionStep>
)
