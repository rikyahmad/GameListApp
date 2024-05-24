package com.staygrateful.feature_favorites.presentation.view

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.staygrateful.core.component.GameItem
import com.staygrateful.core.component.GameList
import com.staygrateful.core.component.InfiniteData
import com.staygrateful.core.component.SimpleAppBar
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.theme.LightRippleTheme
import com.staygrateful.feature_favorites.presentation.viewmodel.GameFavoriteViewModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameFavoriteScreen(
    modifier: Modifier = Modifier,
    pageSize: Int = 10,
    padding: Dp = 25.dp,
    viewmodel: GameFavoriteViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (GameEntity) -> Unit = {},
    onBackPressed: () -> Unit = {},
    onBottomReached: suspend (InfiniteData) -> Unit = {},
) {

    val items by viewmodel.items.collectAsState(initial = emptyList())

    val context = LocalContext.current

    Scaffold(
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
                        title = "FAVORITE GAMES",
                        leadingIconSize = 45.dp,
                        fontSize = 18.sp,
                        leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
                        leadingIconColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary,
                        onLeadingClick = {
                            onBackPressed.invoke()
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        /*LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            items(favoriteGames.itemCount) { index ->
                favoriteGames[index]?.let {
                    GameItem(it, animatedVisibilityScope, onItemClick)
                }
            }
        }*/
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            GameList(
                pageSize = pageSize,
                modifier = modifier
                    .background(Color.White),
                items = items,
                contentPadding = PaddingValues(padding),
                spacer = padding,
                animatedVisibilityScope = animatedVisibilityScope,
                onItemClick = onItemClick,
                onBottomReached = { data ->
                    onBottomReached.invoke(data)
                    /*viewmodel.collect(
                        data.nextPage,
                        data.pageSize,
                        data.state == InfiniteState.Refresh
                    )*/
                }
            )
        }
    }
}