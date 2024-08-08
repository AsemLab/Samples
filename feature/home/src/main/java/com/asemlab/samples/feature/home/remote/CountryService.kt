package com.asemlab.samples.feature.home.remote

import com.asemlab.samples.feature.home.models.CountryResponseItem
import retrofit2.http.GET

interface CountryService {

    @GET("all?fields=name,flags,region")
    suspend fun getCountries(): List<CountryResponseItem>

}