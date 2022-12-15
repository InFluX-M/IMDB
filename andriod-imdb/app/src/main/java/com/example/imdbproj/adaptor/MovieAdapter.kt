package com.example.imdbproj.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.Movie

class MovieAdapter(var movies: ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val textViewName = itemView.findViewById<TextView>(R.id.nameMovie)
        val textViewRank = itemView.findViewById<TextView>(R.id.textViewRank)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_layout,parent,false)

        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]
        holder.textViewName.text = (movie.getTitle())
        holder.textViewRank.text = (movie.getRank().toString())

    }

    override fun getItemCount(): Int {
        return movies.size
    }

}


