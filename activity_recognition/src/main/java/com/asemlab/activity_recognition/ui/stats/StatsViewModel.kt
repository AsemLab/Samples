package com.asemlab.activity_recognition.ui.stats

import com.asemlab.activity_recognition.database.ActivitiesRepository
import com.asemlab.activity_recognition.ui.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(activitiesRepository: ActivitiesRepository) :
    MainViewModel(activitiesRepository) {
}