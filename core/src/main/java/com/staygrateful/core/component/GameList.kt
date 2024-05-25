package com.staygrateful.core.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.staygrateful.core.network.local.entity.GameEntity

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameList(
    modifier: Modifier = Modifier,
    items: List<GameEntity>,
    selectedItems: List<GameEntity> = emptyList(),
    pageSize: Int = 10,
    spacer: Dp = 15.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (GameEntity) -> Unit = {},
    onItemLongClick: (GameEntity, Boolean) -> Unit = { a, b -> },
    onBottomReached: suspend (InfiniteData) -> Unit = {},
) {
    if (items.firstOrNull()?.gameId == -1) return

    InfiniteLazyColumn(
        pageSize = pageSize,
        modifier = modifier,
        items = items,
        contentPadding = contentPadding,
        onBottomReached = onBottomReached,
        spacer = spacer
    ) { _, data ->
        GameItem(
            selected = selectedItems.contains(data),
            data,
            animatedVisibilityScope,
            onItemClick,
            onItemLongClick
        )
    }
}
