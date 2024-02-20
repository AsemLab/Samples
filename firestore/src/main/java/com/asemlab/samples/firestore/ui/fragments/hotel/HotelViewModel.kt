package com.asemlab.samples.firestore.ui.fragments.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.firestore.database.HotelsRepository
import com.asemlab.samples.firestore.model.Hotel
import com.asemlab.samples.firestore.model.Rate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val hotelsRepository: HotelsRepository) :
    ViewModel() {

    fun addRate(rate: Rate) {
        viewModelScope.launch {
            hotelsRepository.insertRate(rate)
        }
    }

    fun deleteHotel(hotel: Hotel) {
        viewModelScope.launch {
            hotelsRepository.deleteHotel(hotel)
        }
    }
}