package com.asemlab.samples.ktor.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
// TODO Make the response classes Serializable if don't use Gson
//@Serializable
data class CountryResponseItem(
    @SerializedName("flags")
    val flags: Flags? = null,
    @SerializedName("name")
    val name: Name? = null,
    @SerializedName("region")
    val region: String? = null
)