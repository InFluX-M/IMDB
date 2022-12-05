package com.example.imdbproj.classes.mainClasses

import java.time.LocalDate

class Person(private val id: String) {

    private var name: String? = null
        get() = field
        set(value) {
            field = value
        }

    private var birthDate: LocalDate? = null
        get() = field
        set(value) {
            field = value
        }

    private var deathDate: LocalDate? = null
        get() = field
        set(value) {
            field = value
        }

    private var birthDateMonth: Int? = null
        get() = field
        set(value) {
            field = value
        }

    private var birthDateDay: Int? = null
        get() = field
        set(value) {
            field = value
        }

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

    fun getId() : String {
        return id
    }


}