package com.asemlab.samples.graphql.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.graphql.data.CountriesService
import com.asemlab.samples.graphql.model.DetailedCountry
import com.asemlab.samples.graphql.model.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val countriesService: CountriesService) :
    ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    private val _countryState = MutableStateFlow(CountryState())
    val countryState = _countryState.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            _state.update {
                it.copy(
                    isLoading = false,
                    countries = countriesService.getCountries()
                )
            }
        }
    }


    fun getCountry(code: String) {
        viewModelScope.launch {
            _countryState.update {
                it.copy(isLoading = true)
            }
            _countryState.update {
                it.copy(
                    isLoading = false,
                    selectedCountry = countriesService.getCountry(code)
                )
            }
        }
    }

    fun dismissDialog() {
        viewModelScope.launch {
            _countryState.update {
                it.copy(selectedCountry = null)
            }
        }
    }


    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
    )

    data class CountryState(
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )

}