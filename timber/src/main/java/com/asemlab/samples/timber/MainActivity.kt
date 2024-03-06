package com.asemlab.samples.timber

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO Use Timber for logging
        Timber.d("Debug message")
        Timber.log(Log.ERROR, "Error message")
        Timber.log(Log.INFO, "Info message")
        Timber.log(Log.WARN, "Warn message")
        Timber.v("Verbose message")


        // TODO Change the default tag
        Timber.tag("Main").d("Debug message 2")

        // TODO Log Exception with Timber
        Timber.e(Exception("onCreate"))


    }
}