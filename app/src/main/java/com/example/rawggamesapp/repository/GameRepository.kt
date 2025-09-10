package com.example.rawggamesapp.repository


import com.example.rawggamesapp.network.RetrofitClient

class GameRepository {
//    suspend fun getGames(apiKey: String, page: Int, pageSize: Int) =
//        RetrofitClient.apiService.getGames(
//            apiKey = apiKey,
//            page = page,
//            pageSize = pageSize,
//            ordering = "released",
//            excludeAdditions = true
//        )
    suspend fun getGames(apiKey: String, page: Int, pageSize: Int) =
        RetrofitClient.apiService.getGames(apiKey, page, pageSize)

    suspend fun getGameDetail(id: Int, apiKey: String) =
        RetrofitClient.apiService.getGameDetail(id, apiKey)
}