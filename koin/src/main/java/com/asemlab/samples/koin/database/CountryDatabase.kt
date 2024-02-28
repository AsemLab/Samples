package com.asemlab.samples.koin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asemlab.samples.koin.model.Country

// TODO Used for explanation
@Database(version = 1, exportSchema = false, entities = [Country::class])
abstract class CountryDatabase : RoomDatabase() {

    abstract fun provideCountryDao(): CountryDao

    fun init() {
        with(provideCountryDao()) {
            if (getAll().isEmpty()) {
                insert(Country("Jordan", "Amman"))
            }
        }
    }
}