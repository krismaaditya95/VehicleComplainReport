package com.krismaaditya.vcr.config

import android.app.Application
import com.krismaaditya.vcr.core.bindings.mainBindings
import com.krismaaditya.vcr.main.bindings.dashboardBinding
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@App)

            modules(
                mainBindings,
                dashboardBinding
            )
        }
    }
}