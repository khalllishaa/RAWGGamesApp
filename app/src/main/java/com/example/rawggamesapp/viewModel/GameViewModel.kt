package com.example.rawggamesapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggamesapp.model.Game
import com.example.rawggamesapp.network.ApiService
import com.example.rawggamesapp.network.RetrofitClient
import com.example.rawggamesapp.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    private val _favorites = MutableLiveData<MutableList<Game>>(mutableListOf())
    val favorites: LiveData<List<Game>> = _favorites as LiveData<List<Game>>

    fun addToFavorites(game: Game) {
        if (!_favorites.value!!.contains(game)) {
            _favorites.value!!.add(game)
            _favorites.value = _favorites.value // trigger observer
        }
    }

    fun loadGames(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getGames(apiKey)
                _games.value = response.results
            } catch (e: Exception) {
                _games.value = emptyList()
            }
        }
    }
}

//class GameViewModel : ViewModel() {
//    private val repo = GameRepository()
//    val games = MutableLiveData<List<Game>>()
//
//    fun loadGames(apiKey: String, page: Int = 1, pageSize: Int = 20) {
//        viewModelScope.launch {
//            try {
//                val response = repo.getGames(apiKey, page, pageSize)
//                games.postValue(response.results)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//fun loadGames(apiKey: String, page: Int = 1, pageSize: Int = 20) {
//    viewModelScope.launch {
//        try {
//            val response = repo.getGames(apiKey, page, pageSize)
//            val filtered = response.results.filter { !it.released.isNullOrEmpty() }
//            games.postValue(filtered)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//}

