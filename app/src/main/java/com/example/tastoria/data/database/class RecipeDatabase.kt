package com.example.tastoria.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tastoria.data.model.AnalyzedInstruction
import com.example.tastoria.data.model.Converters
import com.example.tastoria.data.model.ExtendedIngredient
import com.example.tastoria.data.model.FavoriteRecipe
import com.example.tastoria.data.model.Recipe

@Database(entities = [FavoriteRecipe::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}



// Define the database and specify the entities and version
//@Database(entities = [Recipe::class, ExtendedIngredient::class, AnalyzedInstruction::class], version = DATABASE_VERSION, exportSchema = false)
//@TypeConverters(Converters::class)
//abstract class RecipeDatabase : RoomDatabase() {
//
//    // Abstract function to get the DAO
//    abstract fun recipeDao(): RecipeDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: RecipeDatabase? = null
//
//        // Function to get the instance of the database
//        fun getDatabase(context: Context): RecipeDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    RecipeDatabase::class.java,
//                    "recipe_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}


//@Database(
//    entities = [Recipe::class],
//    version =1,
//    exportSchema = false
//)
//abstract class RecipeDatabase: RoomDatabase() {
//    abstract fun recipeDao(): RecipeDao
//
//    companion object {
//        private const val DB_NAME = "recipe_database.db"
//
//        private var instance: RecipeDatabase? = null
//
//        operator fun invoke(context: Context) = instance ?: synchronized(Any()) {
//            instance ?: buildDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun buildDatabase(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            RecipeDatabase::class.java,
//            DB_NAME
//        ).build()
//    }
//}