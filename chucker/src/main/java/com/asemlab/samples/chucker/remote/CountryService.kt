package com.asemlab.samples.chucker.remote

import com.asemlab.samples.chucker.models.CountryResponseItem
import retrofit2.http.GET

interface CountryService {

    @GET("all?fields=name,flags,region")
    suspend fun getCountries(): List<CountryResponseItem>

}