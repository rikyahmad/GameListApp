package com.staygrateful.core.extension

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp

/**
 * Extension function to convert an integer value representing pixels to density-independent pixels (DP).
 * @return The converted value in DP.
 */
fun Int.toDp(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )
}

/**
 * Extension function to convert a float value representing density-independent pixels (DP) to pixels.
 * @return The converted value in pixels.
 */
fun Float.toPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
}

@Composable
fun Dp.toPx(): Float {
    val context = LocalContext.current
    return with(LocalContext.current.resources.displayMetrics) {
        this@toPx.value * density
    }
}

fun Float.dpToPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )
}
