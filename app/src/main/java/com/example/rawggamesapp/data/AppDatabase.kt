package com.example.rawggamesapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rawggamesapp.model.FavoriteGame

@Database(entities = [FavoriteGame::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteGameDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "favorite_games_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
