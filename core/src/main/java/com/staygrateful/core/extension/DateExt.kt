package com.staygrateful.core.extension

import java.text.SimpleDateFormat
import java.util.Locale

fun String.convertDateFormat(
    inputPattern: String = "yyyy-MM-dd",
    outputPattern: String = "MMMM d, yyyy",
    localeInput: Locale = Locale.ENGLISH,
    localeOutput: Locale = Locale.ENGLISH
): String {
    try {
        val inputFormat = SimpleDateFormat(inputPattern, localeInput)
        val outputFormat = SimpleDateFormat(outputPattern, localeOutput)
        val date = inputFormat.parse(this)
        if (date != null) {
            return outputFormat.format(date)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return this
}