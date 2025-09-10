package com.example.rawggamesapp.network

import com.example.rawggamesapp.model.Game
import com.example.rawggamesapp.model.GameResponse
import com.example.rawggamesapp.model.GamesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGames(
        @Query("key") key: String = Constants.API_KEY,
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null
    ): GamesListResponse

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") apiKey: String
    ): Game
}
