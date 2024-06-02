package com.staygrateful.core.extension

import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DisplayExtensionsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    fun Int.toDp(): Float {
        val density = InstrumentationRegistry.getInstrumentation().targetContext.resources.displayMetrics.density
        return this / density
    }

    fun Float.toPx(): Float {
        val density = InstrumentationRegistry.getInstrumentation().targetContext.resources.displayMetrics.density
        return this * density
    }

    @Composable
    fun Dp.toPx(): Float {
        val density = LocalDensity.current.density
        return this.value * density
    }

    fun Float.dpToPx(): Float {
        return this * InstrumentationRegistry.getInstrumentation().targetContext.resources.displayMetrics.density
    }

    @Test
    fun testFloatToPx() {
        val dp = 16f
        val expectedPx = dp.toPx()
        val actualPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            InstrumentationRegistry.getInstrumentation().targetContext.resources.displayMetrics
        )
        assertEquals(expectedPx, actualPx, 0.001f)
    }

    @Test
    fun testDpToPx() {
        composeTestRule.setContent {
            val dp = 16.dp
            val expectedPx = dp.toPx()
            val actualPx = dp.value * LocalDensity.current.density
            assertEquals(expectedPx, actualPx, 0.001f)
        }
    }

    @Test
    fun testFloatDpToPx() {
        val dp = 16f
        val expectedPx = dp.dpToPx()
        val actualPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            InstrumentationRegistry.getInstrumentation().targetContext.resources.displayMetrics
        )
        assertEquals(expectedPx, actualPx, 0.001f)
    }
}
