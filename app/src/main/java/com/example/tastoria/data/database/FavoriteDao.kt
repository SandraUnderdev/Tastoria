package com.example.tastoria.data.database

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@androidx.room.Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): LiveData<List<Favorite>>

    @Query("SELECT id FROM favorites")
    suspend fun getAllFavouriteIds(): List<Int>

    @Update
    suspend fun updateFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}

