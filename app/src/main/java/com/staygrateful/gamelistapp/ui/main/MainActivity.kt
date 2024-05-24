package com.staygrateful.gamelistapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.staygrateful.gamelistapp.ui.navgraph.NavGraph
import com.staygrateful.gamelistapp.ui.theme.GameListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameListAppTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}