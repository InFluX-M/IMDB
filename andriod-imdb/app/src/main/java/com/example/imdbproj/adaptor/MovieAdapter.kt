package com.example.imdbproj.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.imdbproj.R
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.mainFragment
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiService
import com.example.imdbproj_1.mainClasses.Rating
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieAdapter(var movies: List<Movie>, val clickListener: (Movie) -> Unit):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        val textViewName = itemView.findViewById<TextView>(R.id.nameMovie)
        val textViewRank = itemView.findViewById<TextView>(R.id.textViewRank)
        val relativeLayout = itemView.findViewById<RelativeLayout>(R.id.relative_main)

        val containerView = itemView.findViewById<ConstraintLayout>(R.id.moviesLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_layout,parent,false)

        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movies[position]
        setRank(movie, holder)

    }

    override fun getItemCount(): Int {
        return movies.size
    }

    private fun setRank(movie: Movie, holder: ViewHolder, ) {

        val apiClient = ApiClient()
        val apiService: ApiService = apiClient.getRetrofit().create(ApiService::class.java)

        apiService.getRating(movie.getTitleId()).enqueue(object : Callback<Rating> {
            override fun onResponse(call: Call<Rating>, response: Response<Rating>) {
                movie.setRank((response.body() as Rating).getAvqRating())
                holder.textViewName.text = (movie.getTitle() + "(" + movie.getStartYear().toString() + ")")
                holder.textViewRank.text = (movie.getRank().toString())

                 // set photo of movie

                holder.containerView.setOnClickListener{
                    clickListener(movie)
                }
            }

            override fun onFailure(call: Call<Rating>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

}


