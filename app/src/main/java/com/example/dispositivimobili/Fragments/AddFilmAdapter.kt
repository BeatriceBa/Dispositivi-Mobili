package com.example.dispositivimobili.Fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivimobili.Movie

class AddFilmAdapter (private val list: List<Movie>) : RecyclerView.Adapter<MovieAddViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAddViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieAddViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieAddViewHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size
}