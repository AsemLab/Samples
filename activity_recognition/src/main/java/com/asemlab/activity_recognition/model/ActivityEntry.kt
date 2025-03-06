package com.asemlab.activity_recognition.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "activities")
data class ActivityEntry(
    val type: String,
    val points: Long,
    val date: Long,
    @PrimaryKey var id: Int? = null
)
