package com.example.imdbproj.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.mainFragment

class MovieAdapter(var movies: List<Movie> ):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        val textViewName = itemView.findViewById<TextView>(R.id.nameMovie)
        val textViewRank = itemView.findViewById<TextView>(R.id.textViewRank)
        val relativeLayout = itemView.findViewById<RelativeLayout>(R.id.relative_main)

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

        holder.relativeLayout.setOnClickListener { v ->
            if (v != null) {
                Toast.makeText(v.context, "movie show", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }

}


