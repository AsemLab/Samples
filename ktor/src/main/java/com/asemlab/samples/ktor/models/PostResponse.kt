package com.asemlab.samples.ktor.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
//@Serializable
data class PostResponse(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("id")
    val id: Int? = null
)