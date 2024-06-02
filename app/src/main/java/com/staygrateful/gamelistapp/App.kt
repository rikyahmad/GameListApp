package com.staygrateful.gamelistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : BaseApp() {

    override fun onCreate() {
        super.onCreate()
    }
}
