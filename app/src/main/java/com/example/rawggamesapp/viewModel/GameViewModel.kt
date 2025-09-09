package com.example.rawggamesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggamesapp.model.Game
import com.example.rawggamesapp.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val repo = GameRepository()
    val games = MutableLiveData<List<Game>>()

    fun loadGames(apiKey: String, page: Int, pageSize: Int) {
        viewModelScope.launch {
            val response = repo.getGames(apiKey, page, pageSize)
            games.postValue(response.results)
        }
    }
}