package com.example.imdbproj.classes.mainClasses

import android.graphics.Movie
import com.google.gson.annotations.SerializedName

class Comment(id: Int, body: String, user: User, movie: Movie, parent: Comment?) {

    @SerializedName("id")
    private val id: Int
    @SerializedName("body")
    private var body: String
    @SerializedName("user")
    private val user: User
    @SerializedName("movie")
    private val movie: Movie
    @SerializedName("parent")
    private val parent: Comment?

    init {
        this.id = id
        this.body = body
        this.user = user
        this.movie = movie
        this.parent = parent
    }

    private var replies: List<Comment>? = null

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

    fun getReplies(): List<Comment> {
        return replies!!
    }

    fun setReplies(replies: List<Comment>) {
        this.replies = replies
    }

}