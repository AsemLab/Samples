package com.asemlab.samples.koin.di

import com.asemlab.samples.koin.model.HomePage
import com.asemlab.samples.koin.remote.CountryService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val YOUTUBE_URL = "https://youtube.com"
const val YOUTUBE_NAME = "YouTube"
const val GOOGLE_URL = "https://google.com"
const val GOOGLE_NAME = "WhatsApp"
const val BASE_URL = "https://restcountries.com/v3.1/"

// TODO Declare modules using qualifiers
val NetworkModule = module {
    single(named(YOUTUBE_NAME)) {
        HomePage(YOUTUBE_URL)
    }

    single(named(GOOGLE_NAME)) {
        HomePage(GOOGLE_URL)
    }

    single {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    single {
        get<Retrofit>().create(CountryService::class.java)
    }
}