package com.asemlab.samples.firestore.ui.fragments.addhotel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.firestore.database.HotelsRepository
import com.asemlab.samples.firestore.model.Hotel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHotelViewModel @Inject constructor(private val hotelsRepository: HotelsRepository) :
    ViewModel() {

    val hotelName = MutableLiveData("")
    val hotelCity = MutableLiveData("")
    val hotelStars = MutableLiveData(1f)
    val haveWifi = MutableLiveData(false)
    val havePool = MutableLiveData(false)


    fun insertHotel(hotel: Hotel, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        viewModelScope.launch {
            hotelsRepository.insertHotel(hotel, onSuccess, onFailure)
        }
    }

    fun getNewHotel(): Hotel {
        return Hotel(
            hotelName.value!!,
            hotelCity.value!!,
            hotelStars.value!!.toInt(),
            haveWifi.value!!,
            havePool.value!!
        )
    }

}