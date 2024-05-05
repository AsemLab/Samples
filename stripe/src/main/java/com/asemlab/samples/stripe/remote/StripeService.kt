package com.asemlab.samples.stripe.remote

import com.asemlab.samples.stripe.models.PaymentIntent
import com.stripe.android.EphemeralKey
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface StripeService {

    @POST("ephemeral_keys")
    @Headers("Stripe-Version: 2024-04-10")
    suspend fun createEphemeralKey(@Query("customer") customerID: String): Response<EphemeralKey>

    @POST("payment_intents")
    suspend fun createPaymentIntent(
        @Query("customer") customerID: String,
        @Query("amount") amount: Int,
        @Query("currency") currency: String,
        @Query("automatic_payment_methods[enabled]") autoMethod: Boolean = true
    ): Response<PaymentIntent>

}