package com.asemlab.samples.koin.di

import com.asemlab.samples.koin.model.HomePage
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val YOUTUBE_URL = "https://youtube.com"
const val YOUTUBE_NAME = "YouTube"
const val GOOGLE_URL = "https://google.com"
const val GOOGLE_NAME = "WhatsApp"

// TODO Declare modules using qualifiers
val NetworkModule = module {
    single(named(YOUTUBE_NAME)) {
        HomePage(YOUTUBE_URL)
    }

    single(named(GOOGLE_NAME)) {
        HomePage(GOOGLE_URL)
    }
}