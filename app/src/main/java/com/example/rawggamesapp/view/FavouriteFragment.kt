package com.example.rawggamesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.rawggamesapp.R
//import com.example.rawggamesapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = "Belum ada favorite games 🙂"

        return view
    }
}
