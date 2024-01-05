package com.asemlab.samples.ktor.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.ktor.models.CountryResponseItem
import com.asemlab.samples.ktor.remote.CountryService
import kotlinx.coroutines.launch

class MainViewModel(val countryService: CountryService) : ViewModel() {

    val countries = MutableLiveData<List<CountryResponseItem>>(emptyList())

    fun getCountries() {
        viewModelScope.launch {
            countries.value = countryService.getCountries()
        }
    }

}

class MainViewModelFactory(val countryService: CountryService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(countryService) as T
        else throw IllegalArgumentException("Incompatible ViewModel class")
    }
}