package com.asemlab.samples.chucker.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.chucker.models.CountryResponseItem
import com.asemlab.samples.chucker.models.PostResponse
import com.asemlab.samples.chucker.remote.CountryService
import com.asemlab.samples.chucker.remote.PostService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val countryService: CountryService,
    val postService: PostService
) : ViewModel() {

    val countries = MutableLiveData<List<CountryResponseItem>>(emptyList())
    val newPost = MutableLiveData<PostResponse>()

    fun getCountries() {
        viewModelScope.launch {
            countries.value = countryService.getCountries()
        }
    }

    fun createPost(post: PostResponse) {
        viewModelScope.launch {
            newPost.value = postService.createPost(post)
        }
    }

}