package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rawggamesapp.R
import com.example.rawggamesapp.viewModel.GameViewModel
import com.squareup.picasso.Picasso

class GameDetailFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val view = inflater.inflate(R.layout.game_detail, container, false)

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvReleased = view.findViewById<TextView>(R.id.tvReleased)
        val ivGame = view.findViewById<ImageView>(R.id.ivGame)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)

        val description = arguments?.getString("description") ?: "No description available"

        tvDescription.text = description

        // ambil data dari arguments
//        val gameId = arguments?.getInt("id") ?: 0
//
//        viewModel.loadGameDetail(gameId) { game ->
//            if (game != null) {
//                tvName.text = game.name
//                tvReleased.text = game.released ?: "Unknown"
//                tvDescription.text = game.description ?: "No description available"
//                Picasso.get().load(game.backgroundImage).into(ivGame)
//            }
//        }

        val gameId = arguments?.getInt("id") ?: 0
        viewModel.loadGameDetail(gameId) { game ->
            if (game != null) {
                tvName.text = game.name
                tvReleased.text = game.released ?: "Unknown"
                tvDescription.text = game.description ?: "No description available"
                Picasso.get().load(game.backgroundImage).into(ivGame)
            }
        }

        return view
    }
}
