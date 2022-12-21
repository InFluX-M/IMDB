package com.example.imdbproj.retrofit

import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {


    @GET("/movies")
    fun getAllMovies(): Call<List<Movie>>

    @POST("/movies")
    fun addMovie(@Body movie: Movie): Call<Movie>


}