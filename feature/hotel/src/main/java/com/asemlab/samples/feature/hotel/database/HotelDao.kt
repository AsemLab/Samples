package com.asemlab.samples.feature.hotel.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.asemlab.samples.feature.hotel.model.Hotel
import com.asemlab.samples.feature.hotel.model.Rate

@Dao
interface HotelDao {

    @Insert
    fun addHotel(hotel: Hotel)

    @Delete
    fun deleteHotel(hotel: Hotel): Int

    @Query("UPDATE hotel SET rate = :rate WHERE id = :hotelId")
    fun updateHotelRate(hotelId: String, rate: Int): Int

    @Query("SELECT * FROM hotel")
    fun getHotels(): LiveData<List<Hotel>>

    @Insert
    fun addRate(rate: Rate)

    @Query("SELECT * FROM rate WHERE hotel_id = :hotelId")
    fun getHotelRates(hotelId: String): List<Rate>

    @RawQuery
    fun sortHotel(query: SupportSQLiteQuery): List<Hotel>

    @RawQuery
    fun filterHotel(query: SupportSQLiteQuery): List<Hotel>

}