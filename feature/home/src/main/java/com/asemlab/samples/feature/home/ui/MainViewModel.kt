package com.asemlab.samples.feature.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.feature.home.models.CountryResponseItem
import com.asemlab.samples.feature.home.remote.CountryService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val countryService: CountryService
) : ViewModel() {

    val countries = MutableLiveData<List<CountryResponseItem>>(emptyList())

    fun getCountries() {
        viewModelScope.launch {
            countries.value = countryService.getCountries()
        }
    }

}