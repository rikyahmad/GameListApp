package com.staygrateful.core.utils

import com.staygrateful.core.BuildConfig
import timber.log.Timber

object TimberLog {

    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun d(message: String) {
        Timber.d(message)
    }

    fun e(message: String) {
        Timber.e(message)
    }

    fun i(message: String) {
        Timber.i(message)
    }

    fun v(message: String) {
        Timber.v(message)
    }
}