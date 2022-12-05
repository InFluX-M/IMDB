package com.example.imdbproj.classes.mainClasses

class User(private val username: String, private var password: String): java.io.Serializable {

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
