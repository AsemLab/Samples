package com.asemlab.samples.graphql.data

import com.asemlab.samples.graphql.model.DetailedCountry
import com.asemlab.samples.graphql.model.SimpleCountry

interface CountriesService {

    suspend fun getCountries(): List<SimpleCountry>

    suspend fun getCountry(code: String): DetailedCountry?

}