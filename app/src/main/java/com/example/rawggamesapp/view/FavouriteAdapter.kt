package com.example.rawggamesapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rawggamesapp.databinding.ItemGameBinding
import com.example.rawggamesapp.model.FavoriteGame
import com.squareup.picasso.Picasso

class FavoriteAdapter(
    private var favorites: List<FavoriteGame>,
    private val onDeleteClick: (FavoriteGame) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fav: FavoriteGame) {
            binding.tvName.text = fav.name
            binding.tvReleased.text = fav.released ?: "Unknown"
            Picasso.get().load(fav.backgroundImage).into(binding.ivGame)

            binding.ivLike.setOnClickListener {
                onDeleteClick(fav)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount() = favorites.size

    fun setFavorites(newFavorites: List<FavoriteGame>) {
        favorites = newFavorites
        notifyDataSetChanged()
    }
}
