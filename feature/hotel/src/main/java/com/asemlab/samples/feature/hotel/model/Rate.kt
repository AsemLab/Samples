package com.asemlab.samples.feature.hotel.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity(
    foreignKeys = [ForeignKey(
        entity = Hotel::class, parentColumns =
        arrayOf("id"),
        childColumns = arrayOf("hotel_id"), onDelete = ForeignKey.CASCADE
    )]
)
data class Rate(
    val score: Int,
    var comment: String,
    val username: String = "Anonymous",
    @ColumnInfo(name = "hotel_id")
    val hotelId: String,
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
) : Parcelable
