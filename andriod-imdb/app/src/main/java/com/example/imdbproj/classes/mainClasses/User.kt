package com.example.imdbproj.classes.mainClasses

import com.google.gson.annotations.SerializedName

class User(username: String, password: String) : java.io.Serializable {

    @SerializedName("username")
    private val username: String
    @SerializedName("password")
    private var password: String

    init {
        this.username = username
        this.password = password
    }

    fun getUserName() : String {
        return username
    }

    fun getPassword() : String {
        return password
    }

    fun setPassword(newPass: String) {
        password = newPass
    }

}
