package com.asemlab.samples.stripe.utils

import android.util.Log
import com.asemlab.samples.stripe.models.ServerResponse
import retrofit2.Response

suspend fun <T> performRequest(request: suspend () -> Response<T>): ServerResponse<T> {
    return try {
        val body = request()
        if (body.isSuccessful)
            ServerResponse.Success(body.body()!!)
        else
            ServerResponse.Error(
                body.errorBody()?.string() ?: "Empty error message",
                body.code()
            )
    } catch (e: Exception) {
        Log.e("performRequest", "${e.message}")
        throw e
    }
}