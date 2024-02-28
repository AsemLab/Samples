package com.asemlab.samples.firestore.database

import com.asemlab.samples.firestore.model.Filter
import com.asemlab.samples.firestore.model.Hotel
import com.asemlab.samples.firestore.model.Rate
import com.asemlab.samples.firestore.model.SortBy

interface HotelsRepository {

    suspend fun getHotels(onSuccess: (List<Hotel>) -> Unit)

    suspend fun insertHotel(hotel: Hotel, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    suspend fun deleteHotel(hotel: Hotel, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    suspend fun insertRate(hotel: Hotel, rate: Rate, onSuccess: (List<Rate>) -> Unit, onFailure: (String) -> Unit)

    suspend fun sortHotel(sortBy: SortBy, onSuccess: (List<Hotel>) -> Unit)
    suspend fun filterHotel(filter: Filter, value: Any, onSuccess: (List<Hotel>) -> Unit)

}