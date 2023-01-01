package com.example.imdbproj.retrofit

import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.Person
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj_1.mainClasses.Rating
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("/movies")
    fun getMovies(): Call<List<Movie>>

    @GET("/movies/{titleId}")
    fun getMovie(@Path("titleId") titleId: String): Call<Movie>

    @GET("/movies/{titleId}/directors")
    fun getDirectors(@Path("titleId") titleId: String): Call<List<Person>>

    @GET("/movies/{titleId}/actors")
    fun getActors(@Path("titleId") titleId: String): Call<List<Person>>

    @GET("/movies/{titleId}/ratings")
    fun getRating( @Path("titleId") titleId: String): Call<Rating>


    @POST("/movies")
    fun addMovie(movie: Movie): Call<Movie>

    @PUT("/movies/{titleId}")
    fun updateMovie(@Path("titleId") titleId: String): Call<Movie>

    @DELETE("/movies/{titleId}")
    fun deleteMovie(@Path("titleId") titleId: String): Call<Movie>



    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Call<User>


}