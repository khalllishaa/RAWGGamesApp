package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rawggamesapp.R
import com.squareup.picasso.Picasso

class GameDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        

        val view = inflater.inflate(R.layout.game_detail, container, false)

        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvReleased = view.findViewById<TextView>(R.id.tvReleased)
        val ivGame = view.findViewById<ImageView>(R.id.ivGame)

        // ambil data dari arguments
        val name = arguments?.getString("name") ?: "Unknown"
        val released = arguments?.getString("released") ?: "Unknown"
        val image = arguments?.getString("image")

        tvName.text = name
        tvReleased.text = released
        Picasso.get().load(image).into(ivGame)

        return view
    }
}
