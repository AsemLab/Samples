package com.asemlab.samples.firestore.database

import com.asemlab.samples.firestore.model.Filter
import com.asemlab.samples.firestore.model.Hotel
import com.asemlab.samples.firestore.model.Rate
import com.asemlab.samples.firestore.model.SortBy

class HotelsRepoImp: HotelsRepository {
    override suspend fun getHotels(): List<Hotel> {
        TODO("Not yet implemented")
    }

    override suspend fun insertHotel(hotel: Hotel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteHotel(hotel: Hotel) {
        TODO("Not yet implemented")
    }

    override suspend fun insertRate(rate: Rate) {
        TODO("Not yet implemented")
    }

    override suspend fun sortHotel(sortBy: SortBy) {
        TODO("Not yet implemented")
    }

    override suspend fun filterHotel(filter: Filter) {
        TODO("Not yet implemented")
    }
}