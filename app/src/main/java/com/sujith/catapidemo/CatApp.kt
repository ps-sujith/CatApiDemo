package com.sujith.catapidemo

import android.app.Application
import com.sujith.catapidemo.data.di.networkModule
import com.sujith.catapidemo.ui.catlist.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CatApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CatApp)
            androidLogger()
            modules(uiModule, networkModule)
        }
    }
}