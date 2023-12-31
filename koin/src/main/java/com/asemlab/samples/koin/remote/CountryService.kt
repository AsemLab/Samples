package com.asemlab.samples.koin.remote

import com.asemlab.samples.koin.model.CountryCapitalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("name/{name}?fields=capital")
    suspend fun getCountryCapital(@Path("name") name: String): Response<CountryCapitalResponse>
}