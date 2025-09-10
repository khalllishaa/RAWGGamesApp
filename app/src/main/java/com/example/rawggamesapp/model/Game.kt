package com.example.rawggamesapp.model
import com.google.gson.annotations.SerializedName

data class Game(
    val id: Int,
    val name: String,
    val released: String? = "Unknown",
//    val backgroundImage: String? = null,
    val description: String? = null,
    val rating: Double = 0.0,
    val ratingTop: Int = 0,
    val ratingsCount: Int = 0,
    @SerializedName("background_image") val backgroundImage: String?
)

