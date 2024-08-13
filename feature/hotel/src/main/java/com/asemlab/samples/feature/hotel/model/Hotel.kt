package com.asemlab.samples.feature.hotel.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
@Entity
data class Hotel(
    var name: String,
    var city: String,
    var stars: Int,
    var freeWifi: Boolean,
    var swimmingPool: Boolean,
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var rate: Int = 5
) : Parcelable

enum class SortBy(val title: String) {
    NAME("name"), STARS("stars"), RATE("rates")
}

enum class Filter(val title: String) {
    HAVE_WIFI("freeWifi"), HAVE_POOL("swimmingPool"), STARS("stars")
}
