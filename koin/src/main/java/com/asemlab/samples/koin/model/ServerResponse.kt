package com.asemlab.samples.koin.model


sealed class ServerResponse<T> {

    data class Success<T>(val responseData: T) : ServerResponse<T>()

    data class Error<T>(val errorBody: String, val code: Int) : ServerResponse<T>()

}

suspend fun <T> ServerResponse<T>.performOnSuccess(onSuccess :suspend (T) -> Unit): ServerResponse<T> {
    if(this is ServerResponse.Success){
        onSuccess(responseData)
    }

    return this
}

fun <T> ServerResponse<T>.performOnError(onError : (String) -> Unit): ServerResponse<T> {
    if(this is ServerResponse.Error){
        onError(errorBody)
    }
    return this
}