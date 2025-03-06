package com.asemlab.activity_recognition.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.asemlab.activity_recognition.model.ActivityEntry

@Dao
interface ActivitiesDAO {

    @Insert
    fun addActivity(activityEntry: ActivityEntry)

    @Query("DELETE FROM activities")
    fun clearData()

    @Query("SELECT *  FROM activities order by id desc")
    fun getAllActivities(): LiveData<List<ActivityEntry>>

    @Query("SELECT *  FROM activities order by id desc limit 1")
    fun getLastActivity(): LiveData<ActivityEntry>

    @Query("SELECT sum(points) FROM activities")
    fun getTotalPoints(): LiveData<Long>

}