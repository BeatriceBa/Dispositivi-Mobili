package com.example.dispositivimobili.Fragments


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivimobili.Movie
import com.example.dispositivimobili.R
import com.example.dispositivimobili.sessionID
import com.squareup.picasso.Picasso
import org.json.JSONObject
import java.net.URL

class MovieAddViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.add_films_layout, parent, false)) {

    private var mTitleView: TextView? = null
    private var mButton : Button
    private var mUrlView : ImageView? = null
    private var id : String = ""

    init {
        mTitleView = itemView.findViewById(R.id.list_title)
        mButton = itemView.findViewById(R.id.addToWatchBtn)
        mUrlView = itemView.findViewById(R.id.list_url)

        mButton.setOnClickListener {
            var jsonobject = JSONObject(URL("https://movieapi.magikarp.fun/addToWatch?session=" + sessionID + "&movie=" + id).readText())
            Toast.makeText(parent.context , "Aggiorna il profilo ;)", Toast.LENGTH_LONG).show()
        }
    }

    fun bind(movie: Movie) {
        mTitleView?.text = movie.title
        id = movie.id
        Picasso.get().load(movie.posterUrl).into(mUrlView)
    }

}