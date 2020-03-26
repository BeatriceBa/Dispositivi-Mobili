package com.example.dispositivimobili.Fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivimobili.Movie

class RemoveFilmAdapter (private val list: List<Movie>) : RecyclerView.Adapter<MovieRemoveViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieRemoveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieRemoveViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieRemoveViewHolder, position: Int) {
        val movie: Movie = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size
}