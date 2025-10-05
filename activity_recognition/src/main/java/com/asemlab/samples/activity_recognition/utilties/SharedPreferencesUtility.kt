package com.asemlab.samples.activity_recognition.utilties

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener

object SharedPreferencesUtility {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Activity) {
        if (!::sharedPreferences.isInitialized) {
            sharedPreferences = context.getPreferences(Context.MODE_PRIVATE)
        }
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun setInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    fun addListener(listener: OnSharedPreferenceChangeListener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun removeListener(listener: OnSharedPreferenceChangeListener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

}