package com.example.imdbproj.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.Movie
import com.squareup.picasso.Picasso

class ImdbMovieAdaptor(context: Context, movies: List<Movie>): RecyclerView.Adapter<ImdbMovieAdaptor.ImdbHolder>() {

    private var context: Context
        get() {
            TODO()
        }
        set(value) {}
    private var movies: List<Movie>
        get() {
            TODO()
        }
        set(value) {}

    init {
        this.context = context
        this.movies = movies
    }


    public class ImdbHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.imageMovie)
        var text: TextView = itemView.findViewById(R.id.nameMovie)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImdbHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.top_movies,parent,false)
        return ImdbHolder(view)
    }

    override fun onBindViewHolder(holder: ImdbHolder, position: Int) {
        val movie: Movie = movies.get(position)
        holder.text.setText(movie.getTitle())

       // Picasso.get().load(movie.getImage()).into(holder.image)

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}