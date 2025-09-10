package com.example.rawggamesapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

import com.example.rawggamesapp.model.FavoriteGame

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_games")
    fun getAll(): LiveData<List<FavoriteGame>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteGame)

    @Delete
    suspend fun delete(favorite: FavoriteGame)
}
