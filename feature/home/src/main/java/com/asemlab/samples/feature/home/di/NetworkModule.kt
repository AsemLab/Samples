package com.asemlab.samples.feature.home.di

import com.asemlab.samples.feature.home.remote.CountryService
import com.asemlab.samples.feature.home.remote.HttpRoutes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideCountryRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()

    }

    @Singleton
    @Provides
    fun provideCountryService(retrofit: Retrofit): CountryService =
        retrofit.create(CountryService::class.java)

}