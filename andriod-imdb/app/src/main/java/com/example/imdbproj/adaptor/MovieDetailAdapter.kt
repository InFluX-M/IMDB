package com.example.imdbproj.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.Movie

class MovieDetailAdapter(var movie: Movie): RecyclerView.Adapter<MovieDetailAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textViewType = itemView.findViewById<TextView>(R.id.textViewType)
        val textViewIsAdult = itemView.findViewById<TextView>(R.id.textViewIsAdullt)
        val textViewStartYear = itemView.findViewById<TextView>(R.id.textViewStartYear)
        val textViewEndYear = itemView.findViewById<TextView>(R.id.textViewEndYear)
        val textViewRuntimeMinutes = itemView.findViewById<TextView>(R.id.textViewRuntimeMinutes)
        val textViewGeners = itemView.findViewById<TextView>(R.id.textViewGenres)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.details_movie,parent,false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewType.text = movie.getType().toString()
        holder.textViewIsAdult.text = movie.isAdult().toString()
        holder.textViewStartYear.text = movie.getStartYear().toString()
        holder.textViewEndYear.text = movie.getEndYear().toString()
        holder.textViewRuntimeMinutes.text = movie.getRuntimeMinutes().toString()
        holder.textViewGeners.text = movie.getGenresList().toString()
    }

    override fun getItemCount(): Int {
        return 1
    }

}