package com.asemlab.samples.firestore.ui.fragments.hotel

import androidx.lifecycle.MutableLiveData
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

    val rates = MutableLiveData<List<Rate>?>(emptyList())

    fun addRate(hotel: Hotel, rate: Rate, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            hotelsRepository.insertRate(hotel, rate, onFailure = onFailure, onSuccess = {
                rates.value = it
            })
        }
    }

    fun deleteHotel(hotel: Hotel, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            hotelsRepository.deleteHotel(hotel, onSuccess, onFailure)
        }
    }
}