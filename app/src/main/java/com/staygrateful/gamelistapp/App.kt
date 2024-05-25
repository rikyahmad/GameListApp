package com.staygrateful.gamelistapp

import android.app.Application
import com.staygrateful.core.utils.TimberLog
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        TimberLog.init()
    }
}
