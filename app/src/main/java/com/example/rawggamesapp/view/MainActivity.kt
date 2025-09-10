package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rawggamesapp.databinding.ActivityMainBinding
import com.example.rawggamesapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GameListFragment())
            .commit()

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_game -> {
                    updateTitle("List Game For You")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, GameListFragment())
                        .commit()
                    true
                }
                R.id.nav_fav -> {
                    updateTitle("Your Favorite Games")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FavoriteFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                showBottomNav()
                hideBackButton()
                updateTitle("List Game For You")
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    fun updateTitle(title: String) {
        supportActionBar?.title = title
    }

    fun hideBottomNav() {
        binding.bottomNav.visibility = View.GONE
    }

    fun showBottomNav() {
        binding.bottomNav.visibility = View.VISIBLE
    }

    fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

}

//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var adapter: GameAdapter
//    private val viewModel: GameViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Setup RecyclerView
//        adapter = GameAdapter(listOf())
//        binding.recyclerView.layoutManager = LinearLayoutManager(this)
//        binding.recyclerView.adapter = adapter
//
//        // Observe LiveData dari ViewModel
//        viewModel.games.observe(this) { games ->
//            Log.d("MAIN", "Update UI dengan ${games.size} data")
//            adapter.setGames(games)
//        }
//
//        // Panggil API lewat ViewModel
//        viewModel.loadGames("40c26eb90f2b42b49874b203ec03ddd8")
//
//        binding.bottomNav.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.nav_game -> {
//                    binding.tvTitle.text = "List Game For You"
//                    true
//                }
//                R.id.nav_fav -> {
//                    binding.tvTitle.text = "Your Favorite Games"
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//}