package com.asemlab.samples.firestore.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.firestore.database.HotelsRepository
import com.asemlab.samples.firestore.model.Filter
import com.asemlab.samples.firestore.model.Hotel
import com.asemlab.samples.firestore.model.Rate
import com.asemlab.samples.firestore.model.SortBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val hotelsRepository: HotelsRepository) :
    ViewModel() {

    val hotels = MutableLiveData<List<Hotel>>()

    init {
        getHotels()
    }

    fun getHotels() {
        viewModelScope.launch {
            hotelsRepository.getHotels {
                hotels.value= it
            }
        }
    }

    fun sortHotels(sortBy: SortBy) {
        viewModelScope.launch {
            hotelsRepository.sortHotel(sortBy){
                hotels.value= it
            }
        }
    }

    fun filterHotels(filter: Filter, value: Any) {
        viewModelScope.launch {
            hotelsRepository.filterHotel(filter, value){
                hotels.value= it
            }
        }
    }
}