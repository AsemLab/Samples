package com.asemlab.samples.chucker.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CountryResponseItem(
    @SerializedName("flags")
    val flags: Flags? = null,
    @SerializedName("name")
    val name: Name? = null,
    @SerializedName("region")
    val region: String? = null
)