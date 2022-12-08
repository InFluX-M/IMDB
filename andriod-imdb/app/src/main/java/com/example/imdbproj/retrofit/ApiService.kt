package com.example.imdbproj.retrofit

import com.example.imdbproj.classes.mainClasses.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/user")
    fun getAllUsers(): Call<List<User?>?>?

}