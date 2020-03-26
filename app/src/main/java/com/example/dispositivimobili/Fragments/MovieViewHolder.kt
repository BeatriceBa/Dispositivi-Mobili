package com.example.dispositivimobili.Fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivimobili.Movie
import com.example.dispositivimobili.R
import com.squareup.picasso.Picasso

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.films_layout, parent, false)) {

    private var mTitleView: TextView? = null
    private var mDescriptionView : TextView? = null
    private var mUrlView : ImageView? = null

    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mDescriptionView = itemView.findViewById(R.id.list_description)
        mUrlView = itemView.findViewById(R.id.list_url)
    }

    fun bind(movie: Movie) {
        mTitleView?.text = movie.title
        mDescriptionView?.text = movie.description
        Picasso.get().load(movie.posterUrl).into(mUrlView);
    }

}