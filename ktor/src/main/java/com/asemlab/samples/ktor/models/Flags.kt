package com.asemlab.samples.ktor.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Flags(
    @SerialName("alt")
    val alt: String? = null,
    @SerialName("png")
    val png: String? = null,
    @SerialName("svg")
    val svg: String? = null
)