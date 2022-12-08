package com.example.imdbproj.classes.mainClasses

import com.example.imdbproj_1.mainClasses.SeriesEpisode
import com.google.gson.annotations.SerializedName

class Movie(titleId: String, type: TitleType, title: String, isAdult: Boolean,
            startYear: Int, endYear: Int, runtimeMinutes: Int, genresList: List<String>) {

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
    @SerializedName("genresList")
    private var genresList: List<String>

    private var episodes: List<SeriesEpisode>? = null

    init {
        this.titleId = titleId
        this.type = type
        this.title = title
        this.isAdult = isAdult
        this.startYear = startYear
        this.endYear = endYear
        this.runtimeMinutes = runtimeMinutes
        this.genresList = genresList
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

    fun setGenresList(newGenresList: List<String>) {
        genresList = newGenresList
    }

    fun getGenresList(): List<String> {
        return genresList
    }


}
