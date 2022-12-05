package com.example.imdbproj.classes.mainClasses

class MovieList(private val id: Int, private val name: String, private val size: Int) {

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