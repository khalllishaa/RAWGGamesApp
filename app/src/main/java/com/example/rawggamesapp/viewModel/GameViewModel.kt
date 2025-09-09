package com.example.rawggamesapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rawggamesapp.model.Game
import com.example.rawggamesapp.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val repo = GameRepository()
    val games = MutableLiveData<List<Game>>()

    fun loadGames(apiKey: String, page: Int = 1, pageSize: Int = 20) {
        viewModelScope.launch {
            try {
                val response = repo.getGames(apiKey, page, pageSize)
                games.postValue(response.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
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

}

