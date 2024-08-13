package com.asemlab.samples.feature.hotel.database

import androidx.lifecycle.LiveData
import com.asemlab.samples.feature.hotel.model.Filter
import com.asemlab.samples.feature.hotel.model.Hotel
import com.asemlab.samples.feature.hotel.model.Rate
import com.asemlab.samples.feature.hotel.model.SortBy

interface HotelsRepository {

    suspend fun getHotels(): LiveData<List<Hotel>>

    suspend fun insertHotel(hotel: Hotel, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    suspend fun deleteHotel(hotel: Hotel, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    suspend fun insertRate(rate: Rate, onSuccess: (List<Rate>) -> Unit, onFailure: (String) -> Unit)

    suspend fun getRates(hotelId: String) : List<Rate>

    suspend fun sortHotel(sortBy: SortBy): List<Hotel>

    suspend fun filterHotel(filter: Filter, value: Any): List<Hotel>

}