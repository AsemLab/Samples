package com.asemlab.samples.feature.hotel.di

import android.content.Context
import androidx.room.Room
import com.asemlab.samples.feature.hotel.database.HotelDatabase
import com.asemlab.samples.feature.hotel.database.HotelsRepoImp
import com.asemlab.samples.feature.hotel.database.HotelsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideHotelsRepository(hotelDatabase: HotelDatabase): HotelsRepository =
        HotelsRepoImp(hotelDatabase.getHotelDao())


    @Singleton
    @Provides
    fun provideHotelDatabase(@ApplicationContext context: Context): HotelDatabase =
        Room.databaseBuilder(context, HotelDatabase::class.java, "hotels_db")
            .fallbackToDestructiveMigration()
            .build()
}