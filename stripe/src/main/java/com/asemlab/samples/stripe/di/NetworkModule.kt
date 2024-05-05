package com.asemlab.samples.stripe.di

import com.asemlab.samples.stripe.BuildConfig
import com.asemlab.samples.stripe.remote.StripeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val BASE_URL = "https://api.stripe.com/v1/"

    @Provides
    @Singleton
    fun providesInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun providesHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            addNetworkInterceptor(interceptor)
            networkInterceptors().add(Interceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.header("Authorization", "Bearer ${BuildConfig.SECRET_KEY}")
                chain.proceed(requestBuilder.build())
            })
        }
        return client.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun providesStripeService(retrofit: Retrofit): StripeService {
        return retrofit.create(StripeService::class.java)
    }


}