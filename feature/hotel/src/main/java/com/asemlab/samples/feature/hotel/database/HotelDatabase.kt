package com.asemlab.samples.feature.hotel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asemlab.samples.feature.hotel.model.Hotel
import com.asemlab.samples.feature.hotel.model.Rate


@Database(version = 3, entities = [Hotel::class, Rate::class], exportSchema = false)
abstract class HotelDatabase : RoomDatabase() {

    abstract fun getHotelDao(): HotelDao

}