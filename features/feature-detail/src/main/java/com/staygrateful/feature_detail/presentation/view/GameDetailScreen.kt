package com.staygrateful.feature_detail.presentation.view

import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.staygrateful.core.component.CollapsingBar
import com.staygrateful.core.component.HtmlText
import com.staygrateful.core.component.SimpleAppBar
import com.staygrateful.core.extension.Unknown
import com.staygrateful.core.extension.copy
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.feature_detail.presentation.component.DetailPlaceholder
import com.staygrateful.feature_detail.presentation.viewmodel.GameDetailViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameDetailScreen(
    data: GameEntity,
    viewmodel: GameDetailViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    headerHeight: Dp = 350.dp,
    onBackPressed: () -> Unit,
    onUpdateFavorite: (Boolean) -> Unit = {},
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    var collapsingValue by remember {
        mutableFloatStateOf(0f)
    }

    var isFavorite by remember {
        mutableStateOf(false)
    }

    var gameEntity by remember {
        mutableStateOf(data)
    }

    val gameDetailResources by viewmodel.gameDetail.collectAsState()

    if(gameDetailResources is Resource.Success) {
        val detailData = gameDetailResources.data
        if(detailData?.id == data.gameId) {
            gameEntity = data.copy(
                description = detailData.description ?: "unknown",
                developer = detailData.developersName ?: "unknown",
            )
        }
    }

    LaunchedEffect(Unit) {
        viewmodel.getDetailGame(data.gameId)
        viewmodel.isFavorite(data.gameId) {
            isFavorite = it
        }
    }

    CollapsingBar(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        headerHeight = headerHeight,
        toolbarColor = MaterialTheme.colorScheme.primary,
        onScrollChange = { value ->
            collapsingValue = value
            println("Collapsing value : $value")
        },
        contentToolbar = {
            SimpleAppBar(
                modifier = Modifier.fillMaxSize(),
                title = "",
                leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
                actionIcon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                containerColor = Color.Transparent,
                leadingIconColor = Color.White,
                actionIconColor = Color.White,
                iconBackgroundColor = Color.DarkGray
                    .copy(
                        (collapsingValue - 0.6f)
                            .coerceAtLeast(0f)
                    ),
                onLeadingClick = {
                    onBackPressed.invoke()
                },
                onActionClick = {
                    onUpdateFavorite.invoke(!isFavorite)
                    viewmodel.updateFavoriteStatus(gameEntity, !isFavorite, onResult = {
                        isFavorite = !isFavorite
                    })
                }
            )
        },
        contentHeader = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(gameEntity.backgroundImage)
                    .diskCacheKey(gameEntity.backgroundImage)
                    .memoryCacheKey(gameEntity.backgroundImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "Loaded Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize()
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${gameEntity.gameId}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 300)
                        }
                    )
            )
        }
    ) {
        item {
            GameDetails(data = gameEntity)
        }
    }
}

@Composable
fun GameDetails(
    modifier: Modifier = Modifier,
    data: GameEntity,
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = data.name,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxWidth()
                .align(Alignment.Start)
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Release Date: ${data.released}",
            fontSize = 14.sp,
            lineHeight = 17.sp,
            fontWeight = FontWeight.Normal,
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Genres: ${data.genres.joinToString(", ")}",
            fontSize = 14.sp,
            lineHeight = 17.sp,
            fontWeight = FontWeight.Normal,
        )
        if (data.developer.isEmpty() || data.developer == Unknown ||
            data.description.isEmpty() || data.description == Unknown) {
            DetailPlaceholder(
                modifier = Modifier.padding(top = 20.dp)
            )
            return
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Developer: ${data.developer}",
            fontSize = 14.sp,
            lineHeight = 17.sp,
            fontWeight = FontWeight.Normal,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 8.dp),
            text = "Description",
            textAlign = TextAlign.Start,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
        )
        HtmlText(
            data.description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            textAlign = TextAlign.Left,
        )
    }
}