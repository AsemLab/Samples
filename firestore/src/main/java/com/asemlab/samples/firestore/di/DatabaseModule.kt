package com.asemlab.samples.firestore.di

import com.asemlab.samples.firestore.database.HotelsRepoImp
import com.asemlab.samples.firestore.database.HotelsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideHotelsRepository(): HotelsRepository = HotelsRepoImp()
}