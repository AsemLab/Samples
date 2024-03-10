package com.asemlab.samples.chuncker.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class Name(
    @SerializedName("common")
    val common: String? = null,
    @SerializedName("official")
    val official: String? = null
)