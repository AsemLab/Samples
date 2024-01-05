package com.asemlab.samples.ktor.remote

import com.asemlab.samples.ktor.models.CountryResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

class CountryServiceImp(private val client: HttpClient) : CountryService {

    override suspend fun getCountries(): List<CountryResponseItem> {
        // TODO Make request
        return client.get(HttpRoutes.GET_ALL) {
            url {
                // TODO Add query parameters to url if there is
                parameters.appendAll("fields", listOf("name,flags,region"))
            }
            headers {
                // TODO Add header parameters to url
                append(HttpHeaders.ContentType, "application/json")
            }
            // TODO Set content type
            contentType(ContentType.Application.Json)

            // TODO Set request body
//            setBody(Country("Jordan", "Asia"))

        }.body() // TODO Deserialize response body
    }

}