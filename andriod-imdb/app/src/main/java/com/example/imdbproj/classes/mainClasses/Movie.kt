package com.example.imdbproj.classes.mainClasses

import android.media.Image
import com.example.imdbproj_1.mainClasses.SeriesEpisode
import com.google.gson.annotations.SerializedName

class Movie(titleId: String, type: TitleType, title: String, isAdult: Boolean,
            startYear: Int, endYear: Int, runtimeMinutes: Int, genresList: String) {

    @SerializedName("titleId")
    private val titleId: String
    @SerializedName("type")
    private var type: TitleType
    @SerializedName("title")
    private var title: String
    @SerializedName("isAdult")
    private var isAdult: Boolean
    @SerializedName("startYear")
    private var startYear: Int
    @SerializedName("endYear")
    private var endYear: Int
    @SerializedName("runtimeMinutes")
    private var runtimeMinutes: Int
    @SerializedName("genres")
    private var genresList: String

    private var rank: Float = 0.0f

    private  var image: Image? = null

    private var episodes: List<SeriesEpisode>? = null

    private lateinit var time: String

    init {
        this.titleId = titleId
        this.type = type
        this.title = title
        this.isAdult = isAdult
        this.startYear = startYear
        this.endYear = endYear
        this.runtimeMinutes = runtimeMinutes
        this.genresList = genresList

        time  = getTime()
    }


    fun getTitleId(): String {
        return titleId
    }

    fun getType(): TitleType {
        return type
    }

    fun setType(newTitleType: TitleType) {
        type = newTitleType
    }

    fun setTitle(newTitle: String) {
        title = newTitle
    }

    fun getTitle(): String {
        return title
    }

    fun setAdult(adult: Boolean) {
        isAdult = adult
    }

    fun isAdult(): Boolean {
        return isAdult
    }

    fun setStartYear(newStartYear: Int) {
        startYear = newStartYear
    }

    fun getStartYear(): Int {
        return startYear
    }

    fun setEndYear(newEndYear: Int) {
        endYear = newEndYear
    }

    fun getEndYear(): Int {
        return endYear
    }

    fun setRuntimeMinutes(newRuntime: Int) {
        runtimeMinutes = newRuntime
    }

    fun getRuntimeMinutes(): Int {
        return runtimeMinutes
    }

    fun setGenresList(newGenresList: String) {
        genresList = newGenresList
    }

    fun getGenresList(): String {
        return genresList
    }

    fun getImage() :Image{
        return image!!
    }

    fun setRank(rank: Float) {
        this.rank = rank
    }

    fun getRank(): String {
        return rank.toString()
    }

    fun getAdult(): String {
        if (isAdult) return "yes"
        return "no"
    }

    fun getYear(): String {
        return ("$startYear to $endYear")
    }

    fun getTime(): String {
        return runtimeMinutes.toString()
    }

    fun getGenres(): String {
        var genres = java.lang.StringBuilder()
        for (g in genresList)
            genres.append(g)
        return genres.toString()
    }


}
