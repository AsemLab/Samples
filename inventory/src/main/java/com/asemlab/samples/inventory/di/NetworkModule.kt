package com.asemlab.samples.inventory.di

import com.asemlab.samples.inventory.BuildConfig
import com.asemlab.samples.inventory.database.InventoryDao
import com.asemlab.samples.inventory.di.RepositoryModule.RetrofitRepository
import com.asemlab.samples.inventory.remote.InventoryService
import com.asemlab.samples.inventory.repos.InventoryRepositoryRetrofit
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

    val BASE_URL = "https://my.api.mockaroo.com/"

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
                requestBuilder.header("X-API-Key", BuildConfig.API_KEY)
                chain.proceed(requestBuilder.build())
            })
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit) = retrofit.create(InventoryService::class.java)


    @Singleton
    @Provides
    @RetrofitRepository
    fun provideRepo(inventoryService: InventoryService, inventoryDao: InventoryDao) =
        InventoryRepositoryRetrofit(inventoryService, inventoryDao)

}