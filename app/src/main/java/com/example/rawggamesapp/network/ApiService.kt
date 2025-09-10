package com.example.rawggamesapp.network

import com.example.rawggamesapp.model.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("ordering") ordering: String = "released",
        @Query("exclude_additions") excludeAdditions: Boolean = true
    ): GameResponse
}
