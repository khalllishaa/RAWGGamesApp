package com.example.rawggamesapp.model

import com.google.gson.annotations.SerializedName

data class GameResponse(
    val id: Int,
    val name: String,
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    val rating: Double,
    @SerializedName("rating_top")
    val ratingTop: Int,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    val description: String?
)

data class GamesListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GameResponse>
)