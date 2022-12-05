package com.example.imdbproj_1.mainClasses

import com.example.imdbproj.classes.mainClasses.Movie

class Rating(private val id: Int, private val movie: Movie,
             private var avgRating: Float, private var numVotes: Int) {

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