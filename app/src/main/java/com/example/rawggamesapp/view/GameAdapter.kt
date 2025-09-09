package com.example.rawggamesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggamesapp.databinding.ItemGameBinding
import com.example.rawggamesapp.model.Game
import com.squareup.picasso.Picasso

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val items = mutableListOf<Game>()

    fun submitList(list: List<Game>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class GameViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game) {
            binding.tvName.text = game.name
            binding.tvReleased.text = game.released
            Picasso.get().load(game.background_image).into(binding.ivGame)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
