package com.example.rawggamesapp.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.rawggamesapp.model.Game
import com.example.rawggamesapp.model.FavoriteGame
import com.example.rawggamesapp.repository.FavoriteRepository
import com.example.rawggamesapp.network.RetrofitClient
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = FavoriteRepository(application.applicationContext)
    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    val favorites: LiveData<List<FavoriteGame>> = repo.getFavorites()

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

    fun addToFavorites(game: Game) {
        val fav = FavoriteGame(
            id = game.id,
            name = game.name,
            released = game.released,
            backgroundImage = game.backgroundImage
        )
        viewModelScope.launch {
            repo.insertFavorite(fav) // simpan ke database
        }
    }

    fun removeFromFavorites(favorite: FavoriteGame) {
        viewModelScope.launch {
            repo.deleteFavorite(favorite)
        }
    }
}
