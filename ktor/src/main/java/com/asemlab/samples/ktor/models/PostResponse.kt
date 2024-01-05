package com.asemlab.samples.ktor.models


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PostResponse(
    @SerialName("title")
    val title: String? = null,
    @SerialName("body")
    val body: String? = null,
    @SerialName("userId")
    val userId: Int? = null,
    @SerialName("id")
    val id: Int? = null
)