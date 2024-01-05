package com.asemlab.samples.ktor

import android.app.Application
import com.asemlab.samples.ktor.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class KtorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // TODO Init Koin
        startKoin {
            androidLogger()
            androidContext(this@KtorApp)
            modules(NetworkModule)
        }
    }
}