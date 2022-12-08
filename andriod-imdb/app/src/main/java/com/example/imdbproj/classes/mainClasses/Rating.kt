package com.example.imdbproj_1.mainClasses

import com.example.imdbproj.classes.mainClasses.Movie
import com.google.gson.annotations.SerializedName

class Rating(id: Int, movie: Movie, avgRating: Float, numVotes: Int) {

    @SerializedName("id")
    private val id: Int
    @SerializedName("movie")
    private val movie: Movie
    @SerializedName("avgRating")
    private var avgRating: Float
    @SerializedName("numVotes")
    private var numVotes: Int

    init {
        this.id = id
        this.movie = movie
        this.avgRating = avgRating
        this.numVotes = numVotes
    }

    fun getId(): Int{
        return id
    }

    fun getMovie(): Movie {
        return movie
    }

    fun setAvqRating(newAvqRating: Float) {
        avgRating = newAvqRating
    }

    fun getAvqRating(): Float {
        return avgRating
    }

    fun setNumVotes(newNumVotes: Int) {
        numVotes = newNumVotes
    }

    fun getNumVotes(): Int{
        return numVotes
    }



}