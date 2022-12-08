package com.example.imdbproj_1.mainClasses

import com.example.imdbproj.classes.mainClasses.Movie
import com.google.gson.annotations.SerializedName

class SeriesEpisode(id: Long, parent: Movie, episode: Movie, seasonNumber: Int, episodeNumber: Int) {


    @SerializedName("id")
    private val id: Long
    @SerializedName("parent")
    private val parent: Movie
    @SerializedName("episode")
    private val episode: Movie
    @SerializedName("seasonNumber")
    private var seasonNumber: Int
    @SerializedName("episodeNumber")
    private var episodeNumber: Int

    init {
        this.id = id
        this.parent = parent
        this.episode = episode
        this.seasonNumber = seasonNumber
        this.episodeNumber = episodeNumber
    }

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