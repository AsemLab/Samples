package com.asemlab.inventory.di

import com.asemlab.inventory.repos.InventoryRepository
import com.asemlab.inventory.repos.InventoryRepositoryRetrofit
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Qualifier
    annotation class RetrofitRepository

    @Binds
    @RetrofitRepository
    abstract fun provideInventoryRepository(inventoryRepositoryRetrofit: InventoryRepositoryRetrofit): InventoryRepository

}