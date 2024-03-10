package com.asemlab.samples.chuncker.di

import android.content.Context
import com.asemlab.samples.chuncker.remote.CountryService
import com.asemlab.samples.chuncker.remote.HttpRoutes
import com.asemlab.samples.chuncker.remote.PostService
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
    // TODO Initialize Chuncker Interceptor
    fun provideChunckerClient(@ApplicationContext context: Context): OkHttpClient {

        // TODO Add configuration for Chuncker Interceptor (Optional)
        val collector = ChuckerCollector(
            context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR // TODO Duration for keep the collected data
        )

        val interceptor =
            ChuckerInterceptor.Builder(context).also {

                // TODO Remove sensitive headers from requests
                it.redactHeaders("Authorization", "Auth-Token")

                // TODO Add Chucker Shortcut to the app
                it.createShortcut(true)

                it.collector(collector)

            }.build()

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    @CountryRetrofit
    // TODO Use Chuncker Interceptor with Retrofit
    fun provideCountryRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(HttpRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()

    }


    @Singleton
    @Provides
    @PostRetrofit
    fun providePostRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(HttpRoutes.POST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
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