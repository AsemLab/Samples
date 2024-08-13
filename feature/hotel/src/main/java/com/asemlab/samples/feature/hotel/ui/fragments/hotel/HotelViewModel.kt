package com.asemlab.samples.feature.hotel.ui.fragments.hotel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.feature.hotel.database.HotelsRepository
import com.asemlab.samples.feature.hotel.model.Hotel
import com.asemlab.samples.feature.hotel.model.Rate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(private val hotelsRepository: HotelsRepository) :
    ViewModel() {

    val rates = MutableLiveData<List<Rate>?>(mutableListOf())

    fun addRate(rate: Rate, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                hotelsRepository.insertRate(rate, onFailure = onFailure, onSuccess = {
                    viewModelScope.launch {
                        rates.value = it.toMutableList()
                    }
                })
            }
        }
    }

    fun deleteHotel(hotel: Hotel, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                hotelsRepository.deleteHotel(hotel, onSuccess, onFailure)
            }
        }
    }

    fun getRates(hotelId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                rates.postValue(hotelsRepository.getRates(hotelId))
            }
        }
    }
}