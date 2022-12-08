package com.example.imdbproj.classes.mainClasses

import com.google.gson.annotations.SerializedName

class MovieList(id: Int, name: String, size: Int) {

    @SerializedName("id")
    private val id: Int
    @SerializedName("name")
    private val name: String
    @SerializedName("size")
    private val size: Int

    init {
        this.id = id
        this.name = name
        this.size = size
    }

    fun getId(): Int{
        return id
    }

    fun getName(): String{
        return name
    }

    fun getSize(): Int{
        return size
    }

}