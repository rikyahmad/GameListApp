package com.staygrateful.feature_search.presentation.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.staygrateful.core.component.ItemMessage
import com.staygrateful.core.component.SearchInput
import com.staygrateful.core.extension.showWithScope
import com.staygrateful.core.network.local.entity.GameEntity
import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.theme.LightRippleTheme
import com.staygrateful.feature_search.R
import com.staygrateful.feature_search.presentation.component.GameListItem
import com.staygrateful.feature_search.presentation.viewmodel.GameSearchViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameSearchScreen(
    modifier: Modifier = Modifier,
    toolbarHeight: Dp = 65.dp,
    viewmodel: GameSearchViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (GameEntity) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    val context = LocalContext.current
    val result by viewmodel.result.collectAsState()
    val items by viewmodel.items.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = { data ->
                    Snackbar(
                        snackbarData = data,
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .windowInsetsPadding(
                WindowInsets.safeDrawing
            ),
        topBar = {
            CompositionLocalProvider(
                LocalRippleTheme provides LightRippleTheme,
                content = {
                    SearchInput(
                        hint = stringResource(R.string.hint_search_games),
                        height = toolbarHeight,
                        animatedVisibilityScope = animatedVisibilityScope,
                        shape = RectangleShape,
                        elevation = 0.dp,
                        border = 0.dp,
                        color = Color.White,
                        padding = PaddingValues(horizontal = 10.dp),
                        leadingIconSize = 45.dp,
                        leadingIcon = Icons.AutoMirrored.Default.ArrowBack,
                        enableClear = true,
                        onLeadingClick = onBackPressed,
                        onValueChange = {
                            viewmodel.searchGames(it.text)
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                items(items.size) { index ->
                    GameListItem(
                        index,
                        items[index],
                        onItemClick = onItemClick
                    )
                }
            }

            if (result is Resource.Success && items.isEmpty()) {
                ItemMessage(
                    color = Color.White,
                    icon = Icons.Default.Search,
                    iconColor = MaterialTheme.colorScheme.primary,
                    title = stringResource(R.string.error_title_no_data_found),
                    description = stringResource(R.string.error_message_no_data_found)
                )
            }
            if (result is Resource.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(25.dp)
                )
            }
        }
    }

    LaunchedEffect(result) {
        if (result is Resource.Error) {
            snackBarHostState.showWithScope(
                coroutineScope,
                context.getString(R.string.error_message, result.message)
            )
        }
    }
}