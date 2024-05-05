package com.asemlab.samples.stripe.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PaymentIntent(
    @SerializedName("client_secret")
    val clientSecret: String? = null
)