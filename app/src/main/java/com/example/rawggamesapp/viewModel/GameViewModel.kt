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
                // konversi GameResponse -> Game
                _games.value = response.results.map { gameResponse ->
                    Game(
                        id = gameResponse.id,
                        name = gameResponse.name,
                        released = gameResponse.released,
                        backgroundImage = gameResponse.backgroundImage,
                        description = gameResponse.description,
                        rating = gameResponse.rating,
                        ratingTop = gameResponse.ratingTop,
                        ratingsCount = gameResponse.ratingsCount
                    )
                }

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

    fun loadGameDetail(id: Int, onResult: (Game?) -> Unit) {
        viewModelScope.launch {
            try {
                val game = RetrofitClient.apiService.getGameDetail(id, "40c26eb90f2b42b49874b203ec03ddd8")
                onResult(game)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }
}
