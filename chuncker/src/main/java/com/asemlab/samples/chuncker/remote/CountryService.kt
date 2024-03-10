package com.asemlab.samples.chuncker.remote

import com.asemlab.samples.chuncker.models.CountryResponseItem
import retrofit2.http.GET

interface CountryService {

    @GET("all?fields=name,flags,region")
    suspend fun getCountries(): List<CountryResponseItem>

}