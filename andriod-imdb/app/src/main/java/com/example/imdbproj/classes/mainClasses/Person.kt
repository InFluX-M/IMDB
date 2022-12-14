package com.example.imdbproj.classes.mainClasses

import android.provider.ContactsContract.Contacts.Photo
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

class Person(id: String) {

    @SerializedName("id")
    private val id: String

    init {
        this.id = id
    }

    @SerializedName("name")
    private var name: String? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("birthDate")
    private var birthDate: LocalDate? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("deathDate")
    private var deathDate: LocalDate? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("birthDateMonth")
    private var birthDateMonth: Int? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("birthDateDay")
    private var birthDateDay: Int? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("professionsList")
    private var professionsList: List<String>? = null
        get() = field
        set(value) {
            field = value
        }

    @SerializedName("knownForTitlesList")
    private var knownForTitlesList: List<String>? = null
        get() = field
        set(value) {
            field = value
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

}