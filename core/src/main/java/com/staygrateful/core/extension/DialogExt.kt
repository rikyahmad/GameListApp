package com.staygrateful.core.extension

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun SnackbarHostState.showWithScope(coroutineScope: CoroutineScope, message: String) {
    coroutineScope.launch(Dispatchers.Main) {
        this@showWithScope.showSnackbar(message = message)
    }
}