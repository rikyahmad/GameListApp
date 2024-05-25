package com.staygrateful.feature_search.presentation.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.staygrateful.core.component.SearchInput
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.theme.LightRippleTheme
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
    val result by viewmodel.result.collectAsState(initial = emptyList())

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
                    SearchInput(
                        hint = "Search Games",
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            items(result.size) { index ->
                GameListItem(
                    index,
                    result[index],
                    onItemClick = onItemClick
                )
            }
        }
    }
}