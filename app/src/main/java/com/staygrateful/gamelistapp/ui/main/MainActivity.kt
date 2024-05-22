package com.staygrateful.gamelistapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.staygrateful.feature_list.presentation.viewmodel.GameListViewModel
import com.staygrateful.gamelistapp.ui.theme.GameListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: GameListViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Button(onClick = {
                        mainViewModel.fetchGames()    
                    }, modifier = Modifier.padding(innerPadding)) {
                        Text(text = "Fetch Games")
                    }
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
    }
}