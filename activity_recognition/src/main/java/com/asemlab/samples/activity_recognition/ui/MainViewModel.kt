package com.asemlab.samples.activity_recognition.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.activity_recognition.database.ActivitiesRepository
import com.asemlab.samples.activity_recognition.model.ActivityEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(private val activitiesRepository: ActivitiesRepository) :
    ViewModel() {

    val entries = MediatorLiveData<List<ActivityEntry>>(emptyList())
    var lastEntry: LiveData<ActivityEntry> = MutableLiveData()
    var totalSteps: LiveData<Long> = MutableLiveData()

    fun addActivity(activityEntry: ActivityEntry) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if(activityEntry.points > 0)
                    activitiesRepository.addActivity(activityEntry)
            }
        }
    }

    fun getAllActivities() {
        viewModelScope.launch {
            entries.addSource(activitiesRepository.getAllActivities(), entries::setValue)
        }
    }

    fun getLastActivity() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                lastEntry = activitiesRepository.getLastActivity()
            }
        }
    }

    fun clearData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                activitiesRepository.clearData()
                getAllActivities()
            }
        }
    }

    fun getTotalPoints() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                totalSteps = activitiesRepository.getTotalPoints()
            }
        }
    }

    fun addTestingActivity() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                activitiesRepository.addTestingActivity()
            }
        }
    }
}

