package com.asemlab.samples.ktor.remote

import com.asemlab.samples.ktor.models.CountryResponseItem

interface CountryService {

    suspend fun getCountries(): List<CountryResponseItem>

}