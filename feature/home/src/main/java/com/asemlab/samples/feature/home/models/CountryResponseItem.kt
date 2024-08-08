package com.asemlab.samples.feature.home.models


import androidx.annotation.Keep
import com.asemlab.samples.feature.home.models.Flags
import com.asemlab.samples.feature.home.models.Name
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