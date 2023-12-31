package com.asemlab.samples.koin.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CountryCapitalResponseItem(
    @SerializedName("capital")
    val capital: List<String?>? = null
) {

    override fun toString(): String {
        return capital?.let {
            it[0]
        } ?: "Unknown"
    }
}