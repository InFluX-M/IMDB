package com.example.imdbproj.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private var retrofit: Retrofit
    private val BASE_URL = "http://192.168.10.108:8080"

    init {
        retrofit = getClient()
    }

    private fun getClient(): Retrofit {

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }

    fun getRetrofit() : Retrofit {
        return retrofit
    }

}