package com.asemlab.samples.timber

import android.app.Application
import timber.log.Timber

class TimberApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // TODO Use Timber.plant to use your desired logger class
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        else
            Timber.plant(ReleaseTree)

    }

}

