package com.example.rawggamesapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.rawggamesapp.data.AppDatabase
import com.example.rawggamesapp.model.FavoriteGame

class FavoriteRepository(context: Context) {

    private val db: AppDatabase = AppDatabase.getDatabase(context)

    fun getFavorites(): LiveData<List<FavoriteGame>> = db.favoriteGameDao().getAll()

    suspend fun insertFavorite(fav: FavoriteGame) {
        db.favoriteGameDao().insert(fav)
    }

    suspend fun deleteFavorite(fav: FavoriteGame) {
        db.favoriteGameDao().delete(fav)
    }
}
