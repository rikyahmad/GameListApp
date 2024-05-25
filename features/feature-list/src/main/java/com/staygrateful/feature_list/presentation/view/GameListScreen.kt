package com.staygrateful.feature_list.presentation.view

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.staygrateful.core.component.GameList
import com.staygrateful.core.component.InfiniteData
import com.staygrateful.core.component.InfiniteState
import com.staygrateful.core.component.SearchInput
import com.staygrateful.core.component.SimpleAppBar
import com.staygrateful.core.extension.showWithScope
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.theme.LightRippleTheme
import com.staygrateful.feature_list.presentation.viewmodel.GameListViewModel

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.GameListScreen(
    modifier: Modifier = Modifier,
    pageSize: Int = 20,
    searchHeight: Dp = 55.dp,
    padding: Dp = 25.dp,
    paddingVerticalSearch: Dp = 20.dp,
    viewmodel: GameListViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (GameEntity) -> Unit = {},
    onSearchClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
    onSearchFocusChange: (FocusState) -> Unit = {},
    onBottomReached: suspend (InfiniteData) -> Unit = {},
) {
    val isConnected by viewmodel.isConnected.collectAsState()
    val items by viewmodel.items.collectAsState(initial = emptyList())
    val result by viewmodel.result.collectAsState()
    val pullRefreshState = rememberPullToRefreshState()
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

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
                    SimpleAppBar(
                        title = "RAWG GAMES",
                        actionIconSize = 40.dp,
                        fontSize = 18.sp,
                        actionIcon = Icons.Default.Favorite,
                        actionIconColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary,
                        onActionClick = {
                            onFavoriteClick.invoke()
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .nestedScroll(pullRefreshState.nestedScrollConnection)
                .padding(innerPadding),
        ) {
            GameList(
                pageSize = pageSize,
                modifier = modifier
                    .background(Color.White),
                items = items,
                contentPadding = PaddingValues(
                    start = padding,
                    end = padding,
                    top = searchHeight + (paddingVerticalSearch * 2),
                    bottom = padding
                ),
                spacer = padding,
                animatedVisibilityScope = animatedVisibilityScope,
                onItemClick = onItemClick,
                onBottomReached = { data ->
                    onBottomReached.invoke(data)
                    viewmodel.collect(
                        data.nextPage,
                        data.pageSize,
                        data.state == InfiniteState.Refresh,
                    )
                    pullRefreshState.endRefresh()
                }
            )
            SearchInput(
                hint = "Search Games",
                height = searchHeight,
                animatedVisibilityScope = animatedVisibilityScope,
                margin = PaddingValues(horizontal = padding, vertical = paddingVerticalSearch),
                onFocusChange = { focus ->
                    onSearchFocusChange.invoke(focus)
                },
                onLeadingClick = onSearchClick
            )
            PullToRefreshContainer(
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                contentColor = Color.White,
                containerColor = MaterialTheme.colorScheme.primary,
            )
        }
    }

    LaunchedEffect(pullRefreshState.isRefreshing) {
        viewmodel.collect(
            1,
            pageSize,
            true,
        )
        pullRefreshState.endRefresh()
    }

    LaunchedEffect(result) {
        when (result) {
            is Resource.Loading -> {}
            is Resource.Success -> {}
            is Resource.Error -> {
                snackBarHostState.showWithScope(coroutineScope, "Error, ${result.message}")
            }
        }
    }

    LaunchedEffect(isConnected) {
        if (!isConnected) {
            snackBarHostState.showWithScope(coroutineScope, "No Internet Connection")
        }
    }
}
