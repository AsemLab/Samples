package com.asemlab.samples.chuncker.di

import com.asemlab.samples.chuncker.remote.CountryService
import com.asemlab.samples.chuncker.remote.HttpRoutes
import com.asemlab.samples.chuncker.remote.PostService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Qualifier
    annotation class CountryRetrofit

    @Qualifier
    annotation class PostRetrofit

    @Singleton
    @Provides
    @CountryRetrofit
    fun provideCountryRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    @PostRetrofit
    fun providePostRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(HttpRoutes.POST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideCountryService(@CountryRetrofit retrofit: Retrofit): CountryService =
        retrofit.create(CountryService::class.java)

    @Singleton
    @Provides
    fun providePostService(@PostRetrofit retrofit: Retrofit): PostService =
        retrofit.create(PostService::class.java)


}