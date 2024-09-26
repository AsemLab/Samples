package com.asemlab.samples.autofill

import android.app.Application
import androidx.lifecycle.MutableLiveData

class AutoFillApp : Application() {

    val otp = MutableLiveData<String>()
}