package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rawggamesapp.viewModel.GameViewModel
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggamesapp.R

class GameListFragment : Fragment() {

    private lateinit var adapter: GameAdapter
    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        adapter = GameAdapter(listOf(), { selectedGame ->
            // klik item -> buka detail
            val detailFragment = GameDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("name", selectedGame.name)
                    putString("released", selectedGame.released)
                    putString("image", selectedGame.backgroundImage)
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
            // klik like -> masuk favorite
            (activity as? MainActivity)?.apply {
                updateTitle("Your Favorite Games")
                hideBottomNav()
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FavoriteFragment())
                .addToBackStack(null)
                .commit()

            // opsional: simpan game ke list favorite di ViewModel / database
            viewModel.addToFavorites(likedGame)
        })

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.games.observe(viewLifecycleOwner) { games ->
            adapter.setGames(games)
        }

        viewModel.loadGames("40c26eb90f2b42b49874b203ec03ddd8")

        return view
    }
}

