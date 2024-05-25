package com.staygrateful.core.utils

import android.os.Handler
import android.os.Looper

object DelayUtil {

    private val handler: Handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable? = null

    fun<T> collectLatest(delay: Long, data: T, onCall: (T) -> Unit) {
        runnable?.let {
            handler.removeCallbacks(it)
        }
        runnable = Runnable{
            onCall.invoke(data)
        }.also {
            handler.postDelayed(it, delay)
        }
    }
}