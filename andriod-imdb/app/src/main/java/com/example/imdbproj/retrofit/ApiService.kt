package com.example.imdbproj.retrofit

import com.example.imdbproj.classes.mainClasses.Comment
import com.example.imdbproj.classes.mainClasses.Movie
import com.example.imdbproj.classes.mainClasses.Person
import com.example.imdbproj.classes.mainClasses.User
import com.example.imdbproj_1.mainClasses.Rating
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //  ------------movie----------------

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
    fun addMovie(@Body movie: Movie): Call<Movie>

    @PUT("/movies/{titleId}")
    fun updateMovie(@Path("titleId") titleId: String): Call<Movie>

    @DELETE("/movies/{titleId}")
    fun deleteMovie(@Path("titleId") titleId: String): Call<Movie>


    // -----------user-----------------------

    @POST("/users/singin")
    @FormUrlEncoded
    fun findUser(@FieldMap map: Map<String,String>): Call<User>



    //------------comment------------------------

    @GET("/comment/{titleId}")
    fun getComments(@Path("titleId") titleId: String): Call<List<Comment>>

    @GET("/comment/replies/{commentId}")
    fun getReplies(@Path("commentId") commentId: Int): Call<List<Comment>>


}