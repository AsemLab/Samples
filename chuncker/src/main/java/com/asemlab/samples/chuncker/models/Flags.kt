package com.asemlab.samples.chuncker.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Flags(
    @SerializedName("alt")
    val alt: String? = null,
    @SerializedName("png")
    val png: String? = null,
    @SerializedName("svg")
    val svg: String? = null
)