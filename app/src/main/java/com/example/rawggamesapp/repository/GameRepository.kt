package com.example.rawggamesapp.repository


import com.example.rawggamesapp.network.RetrofitClient

class GameRepository {
    suspend fun getGames(apiKey: String, page: Int, pageSize: Int) =
        RetrofitClient.api.getGames(
            apiKey = apiKey,
            page = page,
            pageSize = pageSize,
            ordering = "released",
            excludeAdditions = true
        )
}