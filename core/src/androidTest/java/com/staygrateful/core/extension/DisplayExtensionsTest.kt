package com.staygrateful.core.extension

import android.content.res.Resources
import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisplayExtensionsTest {

    @Test
    fun testIntToDp() {
        val pixels = 16
        val expectedDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            pixels.toFloat(),
            Resources.getSystem().displayMetrics
        )
        val actualDp = pixels.toDp()
        assertEquals(expectedDp, actualDp, 0.001f)
    }

    @Test
    fun testFloatToPx() {
        val dp = 16f
        val expectedPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        )
        val actualPx = dp.toPx()
        assertEquals(expectedPx, actualPx, 0.001f)
    }

    @Composable
    @Test
    fun testDpToPx() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val density = context.resources.displayMetrics.density
        val dp = 16.dp
        val expectedPx = dp.value * density
        val actualPx = dp.toPx()
        assertEquals(expectedPx, actualPx, 0.001f)
    }

    @Test
    fun testFloatDpToPx() {
        val dp = 16f
        val expectedPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            Resources.getSystem().displayMetrics
        )
        val actualPx = dp.dpToPx()
        assertEquals(expectedPx, actualPx, 0.001f)
    }
}
