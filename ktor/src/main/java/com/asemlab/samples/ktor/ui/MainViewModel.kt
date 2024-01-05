package com.asemlab.samples.ktor.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.ktor.models.CountryResponseItem
import com.asemlab.samples.ktor.models.PostResponse
import com.asemlab.samples.ktor.remote.CountryService
import com.asemlab.samples.ktor.remote.PostService
import kotlinx.coroutines.launch

class MainViewModel(val countryService: CountryService, val postService: PostService) : ViewModel() {

    val countries = MutableLiveData<List<CountryResponseItem>>(emptyList())
    val newPost = MutableLiveData<PostResponse>()

    fun getCountries() {
        viewModelScope.launch {
            countries.value = countryService.getCountries()
        }
    }

    fun createPost(post: PostResponse){
        viewModelScope.launch {
           newPost.value =  postService.createPost(post)
        }
    }

}

class MainViewModelFactory(val countryService: CountryService, val postService: PostService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java))
            MainViewModel(countryService, postService) as T
        else throw IllegalArgumentException("Incompatible ViewModel class")
    }
}