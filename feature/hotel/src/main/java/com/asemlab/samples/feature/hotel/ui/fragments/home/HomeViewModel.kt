package com.asemlab.samples.feature.hotel.ui.fragments.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.feature.hotel.database.HotelsRepository
import com.asemlab.samples.feature.hotel.model.Filter
import com.asemlab.samples.feature.hotel.model.Hotel
import com.asemlab.samples.feature.hotel.model.SortBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val hotelsRepository: HotelsRepository) :
    ViewModel() {

    val hotels = MediatorLiveData<List<Hotel>>()

    init {
        getHotels()
    }

    fun getHotels() {
        viewModelScope.launch {
            hotels.addSource(
                hotelsRepository.getHotels(),
                hotels::setValue
            )
        }
    }

    fun sortHotels(sortBy: SortBy) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                hotels.postValue(hotelsRepository.sortHotel(sortBy))
            }
        }
    }

    fun filterHotels(filter: Filter, value: Any) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                hotels.postValue(hotelsRepository.filterHotel(filter, value))
            }
        }
    }
}