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
    var rates: List<Rate>? = null,
    val id: UUID? = UUID.randomUUID()
) : Parcelable

enum class SortBy{
    NAME, STARS, RATE
}

enum class Filter{
    HAVE_WIFI, HAVE_POOL, FIVE_STARS
}
