package com.asemlab.samples.ktor.di

import android.accounts.NetworkErrorException
import com.asemlab.samples.ktor.remote.CountryService
import com.asemlab.samples.ktor.remote.CountryServiceImp
import com.asemlab.samples.ktor.remote.HttpRoutes
import com.asemlab.samples.ktor.remote.PostService
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.gson.gson
import org.koin.dsl.module

val NetworkModule = module {
    single<CountryService> {
        CountryServiceImp(
            // TODO Set up client
            HttpClient(Android) {
                install(ContentNegotiation) {
                    gson {
                    }
                    // OR with Kotlin Serialization
//                    json(json = Json {
//                        ignoreUnknownKeys = true
//                    })
                }
                // TODO Set up the base url
                defaultRequest {
                    url(HttpRoutes.BASE_URL)
                    // You can also set up default headers
                }
                // TODO Set up logging
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.HEADERS
                }

                // TODO Add more config
                engine {
                    connectTimeout = 100_000
                    socketTimeout = 100_000
                }
                // TODO Configure HttpRequestRetry
                install(HttpRequestRetry) {
                    retryOnServerErrors(3) //retrying a request if a 5xx response is received
                    maxRetries = 2
                    retryIf { httpRequest, httpResponse ->
                        !httpResponse.status.isSuccess()
                    }
                    retryOnExceptionIf { request, cause ->
                        cause is NetworkErrorException
                    }
                    delayMillis { retry ->
                        retry * 3000L
                    }// retries in 3, 6, 9, etc. seconds
                }
            }
        )
    }

    single {
        PostService(
            HttpClient(Android) {
                install(ContentNegotiation) {
                    gson {
                    }
                }
                defaultRequest {
                    url(HttpRoutes.POST_BASE_URL)
                    contentType(ContentType.Application.Json)
                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.BODY
                }
            }
        )
    }
}