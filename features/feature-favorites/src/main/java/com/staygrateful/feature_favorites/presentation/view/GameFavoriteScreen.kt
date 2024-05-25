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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.staygrateful.core.component.GameList
import com.staygrateful.core.component.InfiniteData
import com.staygrateful.core.component.ItemMessage
import com.staygrateful.core.component.SimpleAppBar
import com.staygrateful.core.network.local.entity.GameEntity
import com.staygrateful.core.theme.LightRippleTheme
import com.staygrateful.feature_favorites.R
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

    val items by viewmodel.items.collectAsState(initial = listOf(GameEntity.initial()))

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
                        title = stringResource(R.string.title_favorite_games),
                        leadingIconSize = 45.dp,
                        fontSize = 17.sp,
                        leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
                        leadingIconColor = Color.White,
                        actionIcon = if (viewmodel.selectedItems.isEmpty()) null else Icons.Default.Delete,
                        actionIconSize = 45.dp,
                        actionIconColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary,
                        onLeadingClick = {
                            onBackPressed.invoke()
                        },
                        onActionClick = {
                            viewmodel.deleteItems(viewmodel.selectedItems.toList())
                            viewmodel.selectedItems.clear()
                        },
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
        ) {
            if (items.isEmpty()) {
                ItemMessage(
                    color = Color.White,
                    iconColor = MaterialTheme.colorScheme.primary,
                    title = stringResource(R.string.error_title_favorite_games),
                    description = stringResource(R.string.error_message_favorite_games)
                )
            }
            GameList(
                pageSize = pageSize,
                modifier = modifier
                    .background(Color.White),
                items = items,
                selectedItems = viewmodel.selectedItems,
                contentPadding = PaddingValues(padding),
                spacer = padding,
                animatedVisibilityScope = animatedVisibilityScope,
                onItemClick = onItemClick,
                onItemLongClick = { data, selected ->
                    viewmodel.onSelectedUpdate(data, selected)
                },
                onBottomReached = { data ->
                    onBottomReached.invoke(data)
                }
            )
        }
    }
}
