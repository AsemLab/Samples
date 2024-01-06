package com.asemlab.samples.ktor.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
//@Serializable
data class Name(
    @SerializedName("common")
    val common: String? = null,
    @SerializedName("official")
    val official: String? = null
)