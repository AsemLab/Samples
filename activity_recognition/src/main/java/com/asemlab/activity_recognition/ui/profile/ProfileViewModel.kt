package com.asemlab.activity_recognition.ui.profile

import com.asemlab.activity_recognition.database.ActivitiesRepository
import com.asemlab.activity_recognition.ui.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(activitiesRepository: ActivitiesRepository) :
    MainViewModel(activitiesRepository) {

}