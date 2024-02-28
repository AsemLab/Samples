package com.asemlab.samples.firestore.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Hotel(
    var name: String,
    var city: String,
    var stars: Int,
    var freeWifi: Boolean,
    var swimmingPool: Boolean,
    var rates: List<Rate>? = listOf(Rate(5, "Nice!"),Rate(2, "Nice!")),
    var id: String = UUID.randomUUID().toString()
) : Parcelable

enum class SortBy(val title: String){
    NAME("name"), STARS("stars"), RATE("rates")
}

enum class Filter(val title: String){
    HAVE_WIFI("freeWifi"), HAVE_POOL("swimmingPool"), STARS("stars")
}
