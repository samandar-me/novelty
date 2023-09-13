package com.sdk.novelty.android

import android.app.Application
import com.sdk.novelty.di.initKoin
import org.koin.android.ext.koin.androidContext

class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
        }
    }
}