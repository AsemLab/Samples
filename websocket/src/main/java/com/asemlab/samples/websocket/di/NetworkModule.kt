package com.asemlab.samples.websocket.di

import com.asemlab.samples.websocket.BuildConfig
import com.asemlab.samples.websocket.remote.OkHttpSocketImp
import com.asemlab.samples.websocket.remote.SocketCallbacks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    val BASE_SOCKET_URL = "ws://192.168.0.107:10001/currency"

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            if(BuildConfig.DEBUG){
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
    fun provideSocketCallbacks(okHttpClient: OkHttpClient, request: Request): SocketCallbacks {
        return OkHttpSocketImp(okHttpClient, request)
    }

}