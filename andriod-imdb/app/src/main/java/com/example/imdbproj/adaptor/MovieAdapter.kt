package com.example.imdbproj.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import com.example.imdbproj_1.mainClasses.Rating
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        setRank(movie, holder)

        //holder.textViewName.text = (movie.getTitle())
        //holder.textViewRank.text = (movie.getRank().toString())

        holder.relativeLayout.setOnClickListener { v ->
            if (v != null) {


            }
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    private fun setRank(movie: Movie, holder: ViewHolder) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getRating(movie.getTitleId()).enqueue(object : Callback<Rating> {
            override fun onResponse(call: Call<Rating>, response: Response<Rating>) {
                movie.setRank((response.body() as Rating).getAvqRating())
                holder.textViewName.text = (movie.getTitle() + "(" + movie.getStartYear().toString() + ")")
                holder.textViewRank.text = (movie.getRank().toString())

                 // set photo of movie
            }

            override fun onFailure(call: Call<Rating>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}


