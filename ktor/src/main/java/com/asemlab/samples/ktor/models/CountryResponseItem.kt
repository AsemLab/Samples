package com.asemlab.samples.ktor.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
// TODO Make the response classes Serializable
@Serializable
data class CountryResponseItem(
    @SerialName("flags")
    val flags: Flags? = null,
    @SerialName("name")
    val name: Name? = null,
    @SerialName("region")
    val region: String? = null
)