package com.example.rawggamesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggamesapp.R
import com.example.rawggamesapp.databinding.ItemGameBinding
import com.example.rawggamesapp.model.Game
import com.squareup.picasso.Picasso

class GameAdapter(
    private var games: List<Game>,
    private val onClick: (Game) -> Unit,
    private val onLikeClick: (Game) -> Unit

) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    inner class GameViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.tvName.text = game.name
            binding.tvReleased.text = game.released ?: "Unknown"
            Picasso.get().load(game.backgroundImage).into(binding.ivGame)

            // klik item -> detail
            binding.root.setOnClickListener {
                onClick(game)
            }

            binding.ivLike.setOnClickListener {
                onLikeClick(game)
                binding.ivLike.setImageResource(R.drawable.baseline_favorite_24)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount() = games.size

    fun setGames(newGames: List<Game>) {
        this.games = newGames
        notifyDataSetChanged()
    }
}
