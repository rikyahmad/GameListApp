package com.staygrateful.core.extension

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class DateExtensionsTest {

    @Test
    fun testConvertDateFormat_defaultPatterns() {
        val inputDate = "2023-05-25"
        val expectedOutput = "May 25, 2023"
        val actualOutput = inputDate.convertDateFormat()
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testConvertDateFormat_customPatterns() {
        val inputDate = "25-05-2023"
        val inputPattern = "dd-MM-yyyy"
        val outputPattern = "yyyy/MM/dd"
        val expectedOutput = "2023/05/25"
        val actualOutput = inputDate.convertDateFormat(inputPattern, outputPattern)
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testConvertDateFormat_differentLocales() {
        val inputDate = "25-05-2023"
        val inputPattern = "dd-MM-yyyy"
        val outputPattern = "MMMM d, yyyy"
        val localeInput = Locale("es", "ES")
        val localeOutput = Locale("es", "ES")
        val expectedOutput = "mayo 25, 2023"
        val actualOutput = inputDate.convertDateFormat(inputPattern, outputPattern, localeInput, localeOutput)
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testConvertDateFormat_invalidDate() {
        val inputDate = "invalid-date"
        val expectedOutput = "invalid-date"
        val actualOutput = inputDate.convertDateFormat()
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun testConvertDateFormat_nullDate() {
        val inputDate: String? = null
        val expectedOutput = ""
        val actualOutput = inputDate?.convertDateFormat() ?: ""
        assertEquals(expectedOutput, actualOutput)
    }
}
