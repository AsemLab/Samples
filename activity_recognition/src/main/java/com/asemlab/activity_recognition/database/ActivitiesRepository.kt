package com.asemlab.activity_recognition.database

import androidx.lifecycle.LiveData
import com.asemlab.activity_recognition.model.ActivityEntry
import com.asemlab.activity_recognition.utilties.ActivityType

class ActivitiesRepository(private val activitiesDAO: ActivitiesDAO) {

    suspend fun addActivity(activityEntry: ActivityEntry) {
        activitiesDAO.addActivity(activityEntry)
    }

    suspend fun getAllActivities(): LiveData<List<ActivityEntry>> {
        return activitiesDAO.getAllActivities()
    }

    suspend fun getLastActivity(): LiveData<ActivityEntry> {
        return activitiesDAO.getLastActivity()
    }

    suspend fun clearData() {
        activitiesDAO.clearData()
    }

    suspend fun getTotalPoints(): LiveData<Long> {
        return activitiesDAO.getTotalPoints()
    }

    suspend fun addTestingActivity() {
        activitiesDAO.addActivity(ActivityEntry(ActivityType.WALKING.name, 500, System.currentTimeMillis()))
    }


}