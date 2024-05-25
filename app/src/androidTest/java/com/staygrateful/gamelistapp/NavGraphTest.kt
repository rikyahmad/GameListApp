package com.staygrateful.gamelistapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.staygrateful.gamelistapp.ui.navgraph.NavGraph
import com.staygrateful.gamelistapp.ui.theme.GameListAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NavGraphTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationToGameDetailScreen() {
        composeTestRule.setContent {
            GameListAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }

        // Simulate clicking on an item in the game list
        composeTestRule.onNodeWithText("Game Item 1").performClick()

        // Verify that the GameDetailScreen is displayed
        composeTestRule.onNodeWithText("Game Detail").assertExists()
    }

    @Test
    fun testNavigationToFavoriteScreen() {
        composeTestRule.setContent {
            GameListAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }

        // Simulate clicking on the favorite button
        composeTestRule.onNodeWithText("Favorites").performClick()

        // Verify that the GameFavoriteScreen is displayed
        composeTestRule.onNodeWithText("Favorite Games").assertExists()
    }

    @Test
    fun testNavigationToSearchScreen() {
        composeTestRule.setContent {
            GameListAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }

        // Simulate clicking on the search button
        composeTestRule.onNodeWithText("Search").performClick()

        // Verify that the GameSearchScreen is displayed
        composeTestRule.onNodeWithText("Search Games").assertExists()
    }
}
