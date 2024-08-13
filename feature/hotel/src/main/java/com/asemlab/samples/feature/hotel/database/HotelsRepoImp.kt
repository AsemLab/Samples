package com.asemlab.samples.feature.hotel.database

import androidx.sqlite.db.SimpleSQLiteQuery
import com.asemlab.samples.feature.hotel.model.Filter
import com.asemlab.samples.feature.hotel.model.Hotel
import com.asemlab.samples.feature.hotel.model.Rate
import com.asemlab.samples.feature.hotel.model.SortBy

class HotelsRepoImp(private val db: HotelDao) : HotelsRepository {

    override suspend fun getHotels() = db.getHotels()

    override suspend fun insertHotel(
        hotel: Hotel,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            db.addHotel(hotel)
            onSuccess()
        }catch (e: Exception){
            onFailure("Insert hotel failed, ${e.message}")
        }
    }

    override suspend fun deleteHotel(
        hotel: Hotel,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        if (db.deleteHotel(hotel) != 0) {
            onSuccess()
        } else {
            onFailure("Delete Hotel failed, not found")
        }
    }

    override suspend fun insertRate(
        rate: Rate,
        onSuccess: (List<Rate>) -> Unit,
        onFailure: (String) -> Unit
    ) {

        db.addRate(rate)

        try {
            val rates = db.getHotelRates(rate.hotelId)
            val count = if ((rates?.size ?: 0) == 0) 1 else rates?.size
            val sum = if ((rates?.size ?: 0) == 0) 5 else rates?.sumOf { it.score }
            val hotelRate = (sum ?: 5) / (count ?: 1)

            db.updateHotelRate(rate.hotelId, hotelRate)
            onSuccess(rates)

        } catch (e: Exception) {
            onFailure("Insert Rate failed, ${e.message}")
        }
    }

    override suspend fun getRates(hotelId: String) = db.getHotelRates(hotelId)

    override suspend fun sortHotel(sortBy: SortBy): List<Hotel> {
        return when (sortBy) {
            SortBy.NAME -> db.sortHotel(SimpleSQLiteQuery("SELECT * FROM hotel ORDER BY name"))
            SortBy.RATE -> db.sortHotel(SimpleSQLiteQuery("SELECT * FROM hotel ORDER BY rate desc"))
            SortBy.STARS -> db.sortHotel(SimpleSQLiteQuery("SELECT * FROM hotel ORDER BY stars desc"))
            else -> db.sortHotel(SimpleSQLiteQuery("SELECT * FROM hotel ORDER BY name"))
        }
    }

    override suspend fun filterHotel(filter: Filter, value: Any): List<Hotel> {
        return when (filter) {
            Filter.HAVE_POOL -> db.filterHotel(SimpleSQLiteQuery("SELECT * FROM hotel where swimmingPool = $value"))
            Filter.HAVE_WIFI -> db.filterHotel(SimpleSQLiteQuery("SELECT * FROM hotel where freeWifi = $value"))
            Filter.STARS -> db.filterHotel(SimpleSQLiteQuery("SELECT * FROM hotel where stars = $value"))
            else -> db.filterHotel(SimpleSQLiteQuery("SELECT * FROM hotel"))
        }
    }
}