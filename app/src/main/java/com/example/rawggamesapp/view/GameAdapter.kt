package com.example.rawggamesapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggamesapp.databinding.ItemGameBinding
import com.example.rawggamesapp.model.Game
import com.squareup.picasso.Picasso

class GameAdapter(private var games: List<Game>) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

//    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
//        val game = games[position]
//        holder.binding.tvName.text = game.name
//        holder.binding.tvReleased.text = game.released
//        Picasso.get().load(game.backgroundImage).into(holder.binding.ivGame)
//    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.binding.tvName.text = game.name
        holder.binding.tvReleased.text = if (!game.released.isNullOrEmpty()) {
            game.released
        } else {
            "Released Date 2021-08-20"
        }

        Picasso.get()
            .load(game.backgroundImage)
            .into(holder.binding.ivGame)
    }

    override fun getItemCount() = games.size

    fun setGames(newGames: List<Game>) {
        for (g in newGames) {
            Log.d("ADAPTER", "Game: ${g.name}, Released: ${g.released}")
        }
        this.games = newGames
        Log.d("ADAPTER", "Masuk ${newGames.size} data")
        this.games = newGames
        notifyDataSetChanged()
    }

}
