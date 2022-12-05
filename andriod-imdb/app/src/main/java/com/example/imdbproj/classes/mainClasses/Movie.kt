package com.example.imdbproj.classes.mainClasses

import com.example.imdbproj_1.mainClasses.SeriesEpisode

class Movie(private val titleId: String, private var type: TitleType,
            private var title: String, private var isAdult: Boolean,
            private var startYear: Int, private var endYear: Int,
            private var runtimeMinutes: Int, private var genresList: List<String>) {

    private var episodes: List<SeriesEpisode>? = null
        get() = field
        set(value) {
            field = value
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
