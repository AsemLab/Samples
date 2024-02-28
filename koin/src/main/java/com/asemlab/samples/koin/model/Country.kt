package com.asemlab.samples.koin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( "countries")
data class Country(
    val name: String,
    val capital: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
){

    override fun toString(): String {
        return "$name, $capital"
    }

}
