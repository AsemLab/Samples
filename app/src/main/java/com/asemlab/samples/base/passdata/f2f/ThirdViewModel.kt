package com.asemlab.samples.base.passdata.f2f

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow

class ThirdViewModel : ViewModel() {

    val topCounter = MutableLiveData<String>()
    val bottomCounter = MutableLiveData<String>()

    val topCounterFlow = MutableSharedFlow<String>()

}