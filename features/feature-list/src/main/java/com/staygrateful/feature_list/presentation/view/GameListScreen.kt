package com.staygrateful.feature_list.presentation.view

import android.widget.Toast
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
import com.staygrateful.core.component.InfiniteData
import com.staygrateful.core.component.InfiniteState
import com.staygrateful.core.component.SearchInput
import com.staygrateful.core.component.SimpleAppBar
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.theme.LightRippleTheme
import com.staygrateful.feature_list.presentation.component.GameList
import com.staygrateful.feature_list.presentation.viewmodel.GameListViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameListScreen(
    modifier: Modifier = Modifier,
    pageSize: Int = 10,
    searchHeight: Dp = 55.dp,
    padding: Dp = 25.dp,
    paddingVerticalSearch: Dp = 20.dp,
    viewmodel: GameListViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (GameEntity) -> Unit = {},
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
                        title = "RAWG GAMES: ${items.size}",
                        actionIconSize = 40.dp,
                        fontSize = 18.sp,
                        actionIcon = Icons.Default.Favorite,
                        actionIconColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.primary,
                        onLeadingClick = {

                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding),
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
                        data.state == InfiniteState.Refresh
                    )
                }
            )
            SearchInput(
                hint = "Search Games",
                height = searchHeight,
                margin = PaddingValues(horizontal = padding, vertical = paddingVerticalSearch),
                onFocusChange = { focus ->
                    //Toast.makeText(context, "Focus Change : ${focus.isFocused}", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}