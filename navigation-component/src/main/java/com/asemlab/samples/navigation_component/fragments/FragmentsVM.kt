package com.asemlab.samples.navigation_component.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentsVM: ViewModel() {

    val title = MutableLiveData("Dummy Title")

    fun setTitle(title: String) {
        this.title.value = title
    }

}