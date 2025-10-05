package com.asemlab.samples.inventory.di

import android.content.Context
import androidx.room.Room
import com.asemlab.samples.inventory.database.InventoryDao
import com.asemlab.samples.inventory.database.InventoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideInventoryDatabase(@ApplicationContext context: Context): InventoryDatabase {
        return Room
            .databaseBuilder(context, InventoryDatabase::class.java, "inventory_db")
            .build()

    }

    @Provides
    @Singleton
    fun provideInventoryDao(inventoryDatabase: InventoryDatabase): InventoryDao {
        return inventoryDatabase.provideInventoryDao()
    }

}