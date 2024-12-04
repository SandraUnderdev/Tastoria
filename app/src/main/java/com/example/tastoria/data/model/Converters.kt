package com.example.tastoria.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.tastoria.data.model.AnalyzedInstruction
import com.example.tastoria.data.model.ExtendedIngredient
import com.example.tastoria.data.model.WinePairing


class Converters {

    // TypeConverter for List<String>
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        val gson = Gson()
        return gson.toJson(value)  // Convert List<String> to JSON string
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        val gson = Gson()
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)  // Convert JSON string back to List<String>
    }

    // TypeConverter for List<AnalyzedInstruction>
    @TypeConverter
    fun fromAnalyzedInstructions(value: List<AnalyzedInstruction>?): String? {
        val gson = Gson()
        return gson.toJson(value)  // Convert List<AnalyzedInstruction> to JSON string
    }

    @TypeConverter
    fun toAnalyzedInstructions(value: String?): List<AnalyzedInstruction>? {
        val gson = Gson()
        val listType = object : TypeToken<List<AnalyzedInstruction>>() {}.type
        return gson.fromJson(value, listType)  // Convert JSON string back to List<AnalyzedInstruction>
    }

    // TypeConverter for List<Any> (used for cuisines, diets, occasions)
    @TypeConverter
    fun fromAnyList(value: List<Any>?): String? {
        val gson = Gson()
        return gson.toJson(value)  // Convert List<Any> to JSON string
    }

    @TypeConverter
    fun toAnyList(value: String?): List<Any>? {
        val gson = Gson()
        val listType = object : TypeToken<List<Any>>() {}.type
        return gson.fromJson(value, listType)  // Convert JSON string back to List<Any>
    }

    // TypeConverter for List<ExtendedIngredient>
    @TypeConverter
    fun fromExtendedIngredients(value: List<ExtendedIngredient>?): String? {
        val gson = Gson()
        return gson.toJson(value)  // Convert List<ExtendedIngredient> to JSON string
    }

    @TypeConverter
    fun toExtendedIngredients(value: String?): List<ExtendedIngredient>? {
        val gson = Gson()
        val listType = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return gson.fromJson(value, listType)  // Convert JSON string back to List<ExtendedIngredient>
    }

    // TypeConverter for WinePairing (can be any custom object you need to handle)
    @TypeConverter
    fun fromWinePairing(value: WinePairing?): String? {
        val gson = Gson()
        return gson.toJson(value)  // Convert WinePairing to JSON string
    }

    @TypeConverter
    fun toWinePairing(value: String?): WinePairing? {
        val gson = Gson()
        return gson.fromJson(value, WinePairing::class.java)  // Convert JSON string back to WinePairing
    }
}

