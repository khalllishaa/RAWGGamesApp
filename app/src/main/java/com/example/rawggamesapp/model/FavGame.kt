package com.example.rawggamesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_games")
data class FavoriteGame(
    @PrimaryKey val id: Int,
    val name: String,
    val released: String?,
    val backgroundImage: String?
)
