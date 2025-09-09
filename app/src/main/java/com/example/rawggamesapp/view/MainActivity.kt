package com.example.rawggamesapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rawggamesapp.databinding.ActivityMainBinding
import com.example.rawggamesapp.viewModel.GameViewModel
import androidx.lifecycle.lifecycleScope
import com.example.rawggamesapp.R
import com.example.rawggamesapp.network.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GameAdapter
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        adapter = GameAdapter(listOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Observe LiveData dari ViewModel
        viewModel.games.observe(this) { games ->
            Log.d("MAIN", "Update UI dengan ${games.size} data")
            adapter.setGames(games)
        }

        // Panggil API lewat ViewModel
        viewModel.loadGames("40c26eb90f2b42b49874b203ec03ddd8")

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_game -> {
                    binding.tvTitle.text = "List Game For You"
                    true
                }
                R.id.nav_fav -> {
                    binding.tvTitle.text = "Your Favorite Games"
                    true
                }
                else -> false
            }
        }
    }
}