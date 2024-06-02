package com.staygrateful.gamelistapp.extension

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

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
        onError.invoke(e)
    }
}