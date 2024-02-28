package com.asemlab.samples.navigation_component.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentsVM: ViewModel() {

    // TODO This liveData will be shared across all fragments by using ActivityVM
    val title = MutableLiveData("Click to change this title")

    fun setTitle(title: String) {
        this.title.value = title
    }

}