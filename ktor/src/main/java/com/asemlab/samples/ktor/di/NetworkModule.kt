package com.asemlab.samples.ktor.di

import com.asemlab.samples.ktor.remote.CountryService
import com.asemlab.samples.ktor.remote.CountryServiceImp
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
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
            }
        )
    }
}