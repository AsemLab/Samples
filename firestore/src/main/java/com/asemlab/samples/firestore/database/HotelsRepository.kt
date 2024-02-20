package com.asemlab.samples.firestore.database

import com.asemlab.samples.firestore.model.Filter
import com.asemlab.samples.firestore.model.Hotel
import com.asemlab.samples.firestore.model.Rate
import com.asemlab.samples.firestore.model.SortBy

interface HotelsRepository {

    suspend fun getHotels(): List<Hotel>

    suspend fun insertHotel(hotel: Hotel)

    suspend fun deleteHotel(hotel: Hotel)

    suspend fun insertRate(rate: Rate)

    suspend fun sortHotel(sortBy: SortBy)

    suspend fun filterHotel(filter: Filter)

}