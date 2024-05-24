package com.staygrateful.core.extension

import android.graphics.Typeface
import android.view.Gravity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

fun FontWeight.toAndroidFontWeight(): Int {
    return when (this) {
        FontWeight.Thin, FontWeight.ExtraLight, FontWeight.Light -> Typeface.NORMAL
        FontWeight.Normal -> Typeface.NORMAL
        FontWeight.Medium, FontWeight.SemiBold -> Typeface.BOLD
        FontWeight.Bold, FontWeight.ExtraBold, FontWeight.Black -> Typeface.BOLD
        else -> Typeface.NORMAL
    }
}

fun TextAlign.toAndroidGravity(): Int {
    return when (this) {
        TextAlign.Left -> Gravity.LEFT
        TextAlign.Start -> Gravity.LEFT
        TextAlign.Right -> Gravity.RIGHT
        TextAlign.End -> Gravity.RIGHT
        TextAlign.Center -> Gravity.CENTER
        TextAlign.Justify -> Gravity.FILL_HORIZONTAL
        else -> Gravity.LEFT
    }
}