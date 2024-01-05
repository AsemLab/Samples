package com.asemlab.samples.ktor.remote

import com.asemlab.samples.ktor.models.CountryResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.path

class CountryServiceImp(private val client: HttpClient) : CountryService {

    override suspend fun getCountries(): List<CountryResponseItem> {
        // TODO Make request
        return client.get(HttpRoutes.GET_ALL) {
            url{
                // TODO Add query parameters to url if there is one
                parameters.appendAll("fields", listOf("name,flags,region"))
            }
        }.body() // TODO Deserialize response body
    }

}