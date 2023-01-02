package com.example.imdbproj.classes.mainClasses

import com.google.gson.annotations.SerializedName

class User(username: String, password: String, email: String) : java.io.Serializable {

    @SerializedName("username")
    private val username: String
    @SerializedName("password")
    private var password: String
    @SerializedName("email")
    private val email: String


    init {
        this.username = username
        this.password = password
        this.email = email
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

    fun getEmail(): String{
        return email
    }

}
