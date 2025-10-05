package com.asemlab.samples.activity_recognition.di

import android.content.Context
import androidx.room.Room
import com.asemlab.samples.activity_recognition.database.ActivitiesDB
import com.asemlab.samples.activity_recognition.database.ActivitiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideActivitiesDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ActivitiesDB::class.java, "activities_db")
            .build()

    @Singleton
    @Provides
    fun provideActivitiesRepo(activitiesDB: ActivitiesDB) =
        ActivitiesRepository(activitiesDB.provideActivitiesDao())

}