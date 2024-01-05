package com.asemlab.samples.ktor.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Name(
    @SerialName("common")
    val common: String? = null,
    @SerialName("official")
    val official: String? = null
)