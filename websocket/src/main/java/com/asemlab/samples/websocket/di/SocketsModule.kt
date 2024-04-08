package com.asemlab.samples.websocket.di

import com.asemlab.samples.websocket.remote.KtorSocketImp
import com.asemlab.samples.websocket.remote.OkHttpSocketImp
import com.asemlab.samples.websocket.remote.SocketCallbacks
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SocketsModule {

    @Binds
    @NetworkModule.OkHttpSocket
    abstract fun provideOkHttpSocketCallbacks(okHttpSocketImp: OkHttpSocketImp): SocketCallbacks

    @Binds
    @NetworkModule.KtorSocket
    abstract fun provideKtorSocketCallbacks(ktorSocketImp: KtorSocketImp): SocketCallbacks

}