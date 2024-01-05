package com.asemlab.samples.ktor.di

import com.asemlab.samples.ktor.remote.CountryService
import com.asemlab.samples.ktor.remote.CountryServiceImp
import com.asemlab.samples.ktor.remote.HttpRoutes
import com.asemlab.samples.ktor.remote.PostService
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val NetworkModule = module {
    single<CountryService> {
        CountryServiceImp(
            // TODO Set up client
            HttpClient(Android) {
                install(ContentNegotiation) {
                    json(json = Json {
                        ignoreUnknownKeys = true
                    })
                }
                // TODO Set up the base url
                defaultRequest {
                    url(HttpRoutes.BASE_URL)
                    // You can also set up default headers
                }
            }
        )
    }

    single {
        PostService(
            HttpClient(Android) {
                install(ContentNegotiation) {
                    json(json = Json {
                        ignoreUnknownKeys = true
                    })
                }
                defaultRequest {
                    url(HttpRoutes.POST_BASE_URL)
                    contentType(ContentType.Application.Json)
                }
            }
        )
    }
}