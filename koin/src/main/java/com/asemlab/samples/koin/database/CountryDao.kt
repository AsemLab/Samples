package com.asemlab.samples.koin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.asemlab.samples.koin.model.Country

@Dao
interface CountryDao {

    @Insert
    fun insert(country: Country)

    @Query("SELECT * FROM countries")
    fun getAll(): List<Country>

    @Query("DELETE FROM countries")
    fun clearUsers()
}