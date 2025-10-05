package com.asemlab.samples.activity_recognition.ui.dashboard

import androidx.lifecycle.MutableLiveData
import com.asemlab.samples.activity_recognition.database.ActivitiesRepository
import com.asemlab.samples.activity_recognition.ui.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(activitiesRepository: ActivitiesRepository) :
    MainViewModel(activitiesRepository) {

    val isServiceRunning = MutableLiveData(false)

    init {
        getLastActivity()
        getTotalPoints()
    }

}