package com.staygrateful.core.extension

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.staygrateful.core.utils.TimberLog

fun <T : Any> NavHostController.tryNavigate(
    route: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
    onError: (Throwable) -> Unit = {}
) {
    try {
        this.navigate(route, navOptions, navigatorExtras)
    } catch (e: Exception) {
        e.printStackTrace()
        TimberLog.e(e.localizedMessage)
        onError.invoke(e)
    }
}