package com.example.imdbproj.repository

import android.util.Log
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.retrofit.ApiClient
import com.example.imdbproj.retrofit.ApiServiceMovie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    companion object {

        fun getAllMovies(): ArrayList<Movie> {

            val apiClient = ApiClient()
            val apiService: ApiServiceMovie = apiClient.getRetrofit().create(ApiServiceMovie::class.java)

            var movies: ArrayList<Movie> = ArrayList<Movie>()

            apiService.getMovies().enqueue(object : Callback<List<Movie>> {

                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                    if (response.isSuccessful) {
                        movies = response.body() as ArrayList<Movie>
                    } else {
                        println(response.body())
                    }
                    Log.d("TAGGG", "gmvhmhgmm")
                }

                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                    Log.d("TAGGG", t.message.toString())

                }
            })

            return movies
        }

        fun getMovieByTitleID(titleID: String): Movie? {

            val apiClient = ApiClient()
            val apiService: ApiServiceMovie = apiClient.getRetrofit().create(ApiServiceMovie::class.java)

            var movie: Movie? = null

            apiService.getMovie(titleID).enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful)
                        movie = response.body() as Movie
                    else
                        println(response.body())
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

            return movie
        }




    }

}