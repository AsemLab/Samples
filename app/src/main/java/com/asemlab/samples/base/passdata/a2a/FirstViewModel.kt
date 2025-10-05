package com.asemlab.samples.base.passdata.a2a

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FirstViewModel: ViewModel() {

    val title = MutableLiveData<String>()

}