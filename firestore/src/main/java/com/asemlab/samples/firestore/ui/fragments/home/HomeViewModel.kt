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
            hotels.value = buildList {
                repeat(7) {
                    add(
                        Hotel(
                            "Hotel $it",
                            "City $it",
                            it,
                            freeWifi = false,
                            swimmingPool = false,
                            buildList {
                                repeat(25) {
                                    add(Rate((1..5).random(), "Nice place!"))
                                }
                            })
                    )
                }
            }
        }
    }

    fun sortHotels(sortBy: SortBy) {
        viewModelScope.launch {
            hotelsRepository.sortHotel(sortBy)
        }
    }

    fun filterHotels(filter: Filter) {
        viewModelScope.launch {
            hotelsRepository.filterHotel(filter)
        }
    }
}