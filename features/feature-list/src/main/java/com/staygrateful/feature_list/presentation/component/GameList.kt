package com.staygrateful.feature_list.presentation.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.staygrateful.core.component.GameItem
import com.staygrateful.core.component.InfiniteData
import com.staygrateful.core.component.InfiniteLazyColumn
import com.staygrateful.core.source.local.entity.GameEntity

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameList(
    modifier: Modifier = Modifier,
    items: List<GameEntity>,
    pageSize: Int = 10,
    spacer: Dp = 15.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (GameEntity) -> Unit = {},
    onBottomReached: suspend (InfiniteData) -> Unit = {},
) {
    InfiniteLazyColumn(
        pageSize = pageSize,
        modifier = modifier,
        items = items,
        contentPadding = contentPadding,
        onBottomReached = onBottomReached,
        spacer = spacer
    ) { _, data ->
        GameItem(data, animatedVisibilityScope, onItemClick)
    }
}
