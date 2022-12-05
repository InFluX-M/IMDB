package com.example.imdbproj.classes.mainClasses

import android.graphics.Movie

class Comment(private val id: Int, private var body: String,
              private val user: User, private val movie: Movie,
              private val parent: Comment?) {

    private var replies: List<Comment>? = null
        get() = field
        set(value) {
            field = value
        }

    fun getId(): Int {
        return id
    }

    fun setBody(newBody: String) {
        body = newBody
    }

    fun getBody(): String {
        return body
    }

    fun getUser(): User {
        return user
    }

    fun getMovie(): Movie {
        return movie
    }

    fun getParent(): Comment {
        return parent!!
    }
}