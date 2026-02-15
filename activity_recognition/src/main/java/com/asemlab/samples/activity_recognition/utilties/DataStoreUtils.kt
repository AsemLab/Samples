package com.asemlab.samples.activity_recognition.utilties

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


object DataStoreUtils {

    // TODO Init DataStore object
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_details")

    val CURRENT_POINTS = intPreferencesKey("current_points")

    suspend fun setCurrentPoints(context: Context, points: Int) {
        context.dataStore.edit { data ->
            data[CURRENT_POINTS] = points
        }
    }

    suspend fun updateCurrentPoints(context: Context, points: Int) {
        context.dataStore.edit { data ->
            data[CURRENT_POINTS] = data[CURRENT_POINTS]?.plus(points) ?: 0
        }
    }

    fun getCurrentPoints(context: Context): Flow<Int> {
        return context.dataStore.data.map {
            it[CURRENT_POINTS] ?: 0
        }
    }

}