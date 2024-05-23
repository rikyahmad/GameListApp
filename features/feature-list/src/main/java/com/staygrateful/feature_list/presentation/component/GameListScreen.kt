package com.staygrateful.feature_list.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.staygrateful.core.component.InfiniteState
import com.staygrateful.feature_list.presentation.viewmodel.GameListViewModel

@Composable
fun GameListScreen(
    modifier: Modifier = Modifier,
    pageSize: Int = 10,
) {
    val gameListViewModel: GameListViewModel = viewModel<GameListViewModel>()

    val items by gameListViewModel.items.collectAsState(initial = emptyList())
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            text = "Total Data: ${items.size}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        GameList(
            pageSize = pageSize,
            modifier = modifier
                .background(Color.White)
                .padding(horizontal = 20.dp),
            items = items,
            spacer = 20.dp
        ) { data ->
            gameListViewModel.collect(
                data.nextPage,
                data.pageSize,
                data.state == InfiniteState.Refresh
            )
        }
    }
}