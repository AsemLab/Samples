package com.asemlab.samples.base.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


object DataStoreUtils /*@Inject constructor(
    private val dataStore: DataStore<Preferences>
)*/ {

    // TODO Init DataStore object
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_details")

    // TODO Init keys
    val USERNAME_KEY = stringPreferencesKey("username")
    val AGE_KEY = intPreferencesKey("age")
    val GENDER_KEY = booleanPreferencesKey("gender")

    // TODO Write to DataStore

//    suspend fun <T> putValue(key: Preferences.Key<T>, newValue: T) {
//        dataStore.edit { data ->
//            data[key] = newValue
//        }
//    }

    suspend fun setUsername(context: Context, username: String) {
        context.dataStore.edit { data ->
            data[USERNAME_KEY] = username
        }
    }

    suspend fun setAge(context: Context, age: Int) {
        context.dataStore.edit { data ->
            data[AGE_KEY] = age
        }
    }

    suspend fun setGender(context: Context, gender: Boolean) {
        context.dataStore.edit { data ->
            data[GENDER_KEY] = gender
        }
    }

    // TODO Read from DataStore

//    suspend fun <T> getValue(
//        key: Preferences.Key<T>,
//        default: T
//    ): T {
//        return dataStore.data.map {
//            it[key] ?: default
//        }.first()
//    }

    suspend fun getUsername(context: Context): String {
        return context.dataStore.data.map {
            it[USERNAME_KEY] ?: ""
        }.first()
    }

    suspend fun getAge(context: Context): Int {
        return context.dataStore.data.map {
            it[AGE_KEY] ?: 0
        }.first()
    }

    suspend fun getGender(context: Context): Boolean {
        return context.dataStore.data.map {
            it[GENDER_KEY] ?: false
        }.first()
    }


}