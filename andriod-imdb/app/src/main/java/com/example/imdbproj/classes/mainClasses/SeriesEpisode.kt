package com.example.imdbproj_1.mainClasses

import com.example.imdbproj.classes.mainClasses.Movie

class SeriesEpisode(private val id: Long,
                    private val parent: Movie, private val episode: Movie,
                    private var seasonNumber: Int, private var episodeNumber: Int) {


    fun getId(): Long {
        return id
    }

    fun getParent(): Movie {
        return parent
    }

    fun getEpisode(): Movie {
        return episode
    }

    fun setSeasonNumbers(newSeasonNumber: Int) {
        seasonNumber = newSeasonNumber
    }

    fun getSeasonNumbers(): Int {
        return seasonNumber
    }

    fun setEpisodeNumber(newEpisodeNumber: Int) {
        episodeNumber = newEpisodeNumber
    }

    fun getEpisodeNumber(): Int {
        return episodeNumber
    }

}