package com.asemlab.samples.activity_recognition

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.asemlab.samples.activity_recognition.utilties.ActivityType
import com.asemlab.samples.activity_recognition.utilties.DetectingMode
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ActivityRecognitionApp : Application() {

    val detectingMode = MutableLiveData<DetectingMode>()
    val activityType = MutableLiveData(ActivityType.WALKING)
    val currentPoints = MutableLiveData(0)


}