package com.example.imdbproj.classes.mainClasses

import android.provider.ContactsContract.Contacts.Photo
import com.google.gson.annotations.SerializedName
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

class Person(id: String, name: String, birthDate: LocalDate, deadDate: LocalDate,
             birthDateMonth: Int, birthDateDay: Int): java.io.Serializable {

    @SerializedName("id")
    private val id: String
    @SerializedName("name")
    private var name: String
    @SerializedName("birthDate")
    private var birthDate: LocalDate
    @SerializedName("deathDate")
    private var deathDate: LocalDate
    @SerializedName("birthDateMonth")
    private var birthDateMonth: Int
    @SerializedName("birthDateDay")
    private var birthDateDay: Int


    private var professionsList: List<String>? = null
        get() = field
        set(value) {
            field = value
        }

    private var knownForTitlesList: List<String>? = null
        get() = field
        set(value) {
            field = value
        }

    init {
        this.id = id
        this.name = name
        this.birthDate = birthDate
        this.deathDate = deadDate
        this.birthDateDay = birthDateDay
        this.birthDateMonth = birthDateMonth
    }

    private lateinit var photo: Photo

    fun getId() : String {
        return id
    }

    fun setPhoto(photo: Photo) {
        this.photo = photo
    }

    fun getPhoto(): Photo {
        return photo
    }

    fun getName(): String{
        return name
    }

    fun setName(name2: String){
        name = name2
    }


}