package com.asemlab.samples.koin

import android.app.Application
import com.asemlab.samples.koin.di.DaoModule
import com.asemlab.samples.koin.di.DatabaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KoinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // TODO Init Koin
        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(DatabaseModule)
            modules(DaoModule)
        }
    }
}