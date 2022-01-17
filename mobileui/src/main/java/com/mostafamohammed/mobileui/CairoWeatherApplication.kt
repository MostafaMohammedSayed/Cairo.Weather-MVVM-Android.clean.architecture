package com.mostafamohammed.mobileui

import android.app.Application
import com.mostafamohammed.mobileui.di.appModule
import com.mostafamohammed.mobileui.di.repoModule
import com.mostafamohammed.mobileui.di.viewModelModule
import org.koin.core.context.startKoin

class CairoWeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, viewModelModule, repoModule))
        }
    }
}