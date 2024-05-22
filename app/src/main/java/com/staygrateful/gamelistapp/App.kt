package com.staygrateful.gamelistapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for TodoListApp.
 *
 * This class represents the application class for App.
 * It initializes Hilt for dependency injection.
 */
@HiltAndroidApp
class App : Application() {

    /**
     * Called when the application is starting.
     *
     * This method is called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created. It initializes any necessary components of the application.
     */
    override fun onCreate() {
        super.onCreate()
    }
}
