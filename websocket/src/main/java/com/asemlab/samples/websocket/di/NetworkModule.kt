package com.asemlab.samples.websocket.di

import com.asemlab.samples.websocket.BuildConfig
import com.asemlab.samples.websocket.remote.KtorSocketImp
import com.asemlab.samples.websocket.remote.OkHttpSocketImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.http.Url
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Qualifier
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    // TODO change to your machine IP address
    val BASE_SOCKET_URL = "ws://192.168.0.100:10001/currency"

    @Qualifier
    annotation class OkHttpSocket

    @Qualifier
    annotation class KtorSocket

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            if (BuildConfig.DEBUG) {
                it.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideWebSocketRequest(): Request {
        return Request.Builder().url(BASE_SOCKET_URL).build()
    }

    @Provides
    @Singleton
    @OkHttpSocket
    fun provideOkHttpSocket(okHttpClient: OkHttpClient, request: Request): OkHttpSocketImp {
        return OkHttpSocketImp(okHttpClient, request)
    }


    @Singleton
    @Provides
    fun provideKtorHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            engine {
                requestTimeout = 10_000
            }

            install(WebSockets) {
            }
        }
    }

    @Provides
    @Singleton
    fun provideWebSocketSessionUrl(): Url {
        return Url(BASE_SOCKET_URL)
    }

    @Provides
    @Singleton
    @KtorSocket
    fun provideKtorSocket(ktorClient: HttpClient, url: Url): KtorSocketImp {
        return KtorSocketImp(ktorClient, url)
    }


}