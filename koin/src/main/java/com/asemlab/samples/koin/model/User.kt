package com.asemlab.samples.koin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String
)
