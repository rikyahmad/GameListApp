package com.staygrateful.gamelistapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.staygrateful.core.component.InfiniteState
import com.staygrateful.feature_list.presentation.component.GameList
import com.staygrateful.feature_list.presentation.component.GameListScreen
import com.staygrateful.feature_list.presentation.viewmodel.GameListViewModel
import com.staygrateful.gamelistapp.ui.theme.GameListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameListAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        GameListScreen(modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding))

                    }
                    /*Button(onClick = {
                        gameListViewModel.collectLatest {
                            Toast.makeText(this, "Value : ${it}", Toast.LENGTH_SHORT).show()
                        }
                    }, modifier = Modifier.padding(innerPadding)) {
                        Text(text = "Fetch Games")
                    }*/
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }
}