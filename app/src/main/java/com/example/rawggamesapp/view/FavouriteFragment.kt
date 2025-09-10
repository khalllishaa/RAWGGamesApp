package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggamesapp.viewModel.GameViewModel
import com.example.rawggamesapp.model.Game
import com.example.rawggamesapp.R

class FavoriteFragment : Fragment() {
    private val viewModel: GameViewModel by activityViewModels()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val emptyTextView = view.findViewById<TextView>(R.id.emptyTextView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavoriteAdapter(listOf()) { favoriteGame ->
            viewModel.removeFromFavorites(favoriteGame)
            Toast.makeText(
                requireContext(),
                "${favoriteGame.name} dihapus dari favorit!",
                Toast.LENGTH_SHORT
            ).show()
        }
        recyclerView.adapter = adapter

        viewModel.favorites.observe(viewLifecycleOwner) { favs ->
            if (favs.isEmpty()) {
                recyclerView.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE
            } else {
                adapter.setFavorites(favs)
                recyclerView.visibility = View.VISIBLE
                emptyTextView.visibility = View.GONE
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.updateTitle("Your Favorite Games")
    }
}
