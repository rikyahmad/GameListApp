package com.staygrateful.gamelistapp.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

class UITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myTest() {

        composeTestRule.setContent {
            Button(onClick = { }) {
                Text(text = "Click Button")
            }
        }

        composeTestRule.onNodeWithText("Click Button").performClick()
    }
}