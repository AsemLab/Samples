package com.asemlab.activity_recognition.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asemlab.activity_recognition.model.ActivityEntry

@Database(entities = [ActivityEntry::class], version = 1, exportSchema = false)
abstract class ActivitiesDB : RoomDatabase() {

    abstract fun provideActivitiesDao(): ActivitiesDAO

}

