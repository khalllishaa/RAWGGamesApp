package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rawggamesapp.viewModel.GameViewModel
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggamesapp.R
import com.example.rawggamesapp.model.Game
import androidx.appcompat.widget.SearchView

class GameListFragment : Fragment() {

    private lateinit var adapter: GameAdapter
    private val viewModel: GameViewModel by activityViewModels()
    private var allGames: List<Game> = listOf()
    private lateinit var emptyTextView: TextView
    private var favorites: List<Game> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        emptyTextView = view.findViewById(R.id.emptyTextView)
        val searchPlate = searchView.findViewById<View>(
            androidx.appcompat.R.id.search_plate
        )
        searchPlate.setBackgroundColor(android.graphics.Color.TRANSPARENT)

        val searchText = searchView.findViewById<TextView>(
            androidx.appcompat.R.id.search_src_text
        )
        searchText.setTextColor(android.graphics.Color.BLACK)
        searchText.setHintTextColor(android.graphics.Color.GRAY)

        val searchIcon = searchView.findViewById<View>(
            androidx.appcompat.R.id.search_mag_icon
        )
        searchIcon.visibility = View.GONE

        adapter = GameAdapter(listOf(), { selectedGame ->
            val detailFragment = GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", selectedGame.id)
                }
            }

            (activity as? MainActivity)?.apply {
                hideBottomNav()
                showBackButton()
                updateTitle("Game Detail")
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit()
        }, { likedGame ->
            viewModel.addToFavorites(likedGame)
            Toast.makeText(requireContext(), "${likedGame.name} ditambahkan ke favorit!", Toast.LENGTH_SHORT).show()
        })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.games.observe(viewLifecycleOwner) { games ->
            allGames = games
            adapter.setGames(games)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterGames(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterGames(newText)
                return true
            }
        })

        viewModel.loadGames("40c26eb90f2b42b49874b203ec03ddd8")

        return view
    }

    private fun filterGames(query: String?) {
        val filtered = if (!query.isNullOrEmpty()) {
            allGames.filter { it.name.startsWith(query, ignoreCase = true) }
        } else {
            allGames
        }

        adapter.setGames(filtered)

        // toggle visibility
        emptyTextView.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
    }
}

