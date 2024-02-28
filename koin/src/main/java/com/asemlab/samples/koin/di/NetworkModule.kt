package com.asemlab.samples.koin.di

import com.asemlab.samples.koin.model.SearchEngine
import com.asemlab.samples.koin.remote.CountryService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BING_URL = "https://bing.com/search?q="
const val BING_NAME = "Bing"
const val GOOGLE_URL = "https://google.com/search?q="
const val GOOGLE_NAME = "Google"
const val BASE_URL = "https://restcountries.com/v3.1/"

// TODO Declare modules using qualifiers
val NetworkModule = module {
    single(named(BING_NAME)) {
        SearchEngine(BING_NAME, BING_URL)
    }

    single(named(GOOGLE_NAME)) {
        SearchEngine(GOOGLE_NAME, GOOGLE_URL)
    }

    single {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    single {
        get<Retrofit>().create(CountryService::class.java)
    }
}