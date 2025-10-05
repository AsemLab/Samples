//package com.asemlab.samples.base.di
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
//import androidx.datastore.preferences.SharedPreferencesMigration
//import androidx.datastore.preferences.core.PreferenceDataStoreFactory
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.emptyPreferences
//import androidx.datastore.preferences.preferencesDataStoreFile
//import com.asemlab.screenbrightness.utils.DataStoreUtils
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.SupervisorJob
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DataStoreModule {
//
//    @Singleton
//    @Provides
//    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
//        return PreferenceDataStoreFactory.create(
//            corruptionHandler = ReplaceFileCorruptionHandler(
//                produceNewData = { emptyPreferences() }
//            ),
//            // TODO Migrations can be added here if you're migrating from SharedPreferences
//            //  or changing DataStore schema
//            migrations = listOf(SharedPreferencesMigration(appContext, "your_shared_prefs_name")),
//            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()), // Application-wide scope
//            produceFile = { appContext.preferencesDataStoreFile("user_details") }
//        )
//    }
//
//    @Singleton
//    @Provides
//    fun provideDataStoreUtils(dataStore: DataStore<Preferences>): DataStoreUtils {
//        return DataStoreUtils(dataStore)
//    }
//}