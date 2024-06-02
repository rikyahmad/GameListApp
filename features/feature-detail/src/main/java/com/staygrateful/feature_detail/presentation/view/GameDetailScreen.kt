package com.staygrateful.feature_detail.presentation.view

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.staygrateful.core.extension.showWithScope
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.feature_detail.R
import com.staygrateful.feature_detail.domain.model.GameDetailModel
import com.staygrateful.feature_detail.presentation.component.DetailPlaceholder
import com.staygrateful.feature_detail.presentation.viewmodel.GameDetailViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameDetailScreen(
    data: GameDetailModel,
    viewmodel: GameDetailViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
    headerHeight: Dp = 350.dp,
    onBackPressed: () -> Unit,
    onUpdateFavorite: (Boolean) -> Unit = {},
) {
    val isConnected by viewmodel.isConnected.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val gameDetailResources by viewmodel.gameDetail.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    var collapsingValue by remember {
        mutableFloatStateOf(0f)
    }

    var isFavorite by remember {
        mutableStateOf(false)
    }

    var gameModel by remember {
        mutableStateOf(data)
    }

    LaunchedEffect(gameDetailResources) {
        if (gameDetailResources is Resource.Success) {
            val detailData = gameDetailResources.data
            if (detailData?.id == data.gameId) {
                gameModel = data.copy(
                    description = detailData.description,
                    developer = detailData.developersName,
                )
            }
        } else if (gameDetailResources is Resource.Error) {
            snackBarHostState.showSnackbar(
                context.getString(
                    R.string.error_message, gameDetailResources.message
                )
            )
        }
    }

    LaunchedEffect(Unit) {
        viewmodel.getDetailGame(data.gameId)
        viewmodel.isFavorite(data.gameId) {
            isFavorite = it
        }
    }

    CollapsingBar(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        snackbarState = snackBarHostState,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState, snackbar = { data ->
                Snackbar(
                    snackbarData = data, containerColor = Color.Red, contentColor = Color.White
                )
            })
        },
        headerHeight = headerHeight,
        toolbarColor = MaterialTheme.colorScheme.primary,
        onScrollChange = { value ->
            collapsingValue = value
        },
        contentToolbar = {
            SimpleAppBar(modifier = Modifier.fillMaxSize(),
                leadingIcon = Icons.AutoMirrored.Filled.ArrowBack,
                actionIcon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                containerColor = Color.Transparent,
                leadingIconColor = Color.White,
                actionIconColor = Color.White,
                iconBackgroundColor = Color.DarkGray.copy(
                        (collapsingValue - 0.6f).coerceAtLeast(0f)
                    ),
                onLeadingClick = {
                    onBackPressed.invoke()
                },
                onActionClick = {
                    onUpdateFavorite.invoke(!isFavorite)
                    viewmodel.updateFavoriteStatus(gameModel, !isFavorite, onResult = {
                        isFavorite = !isFavorite
                    })
                })
        },
        contentHeader = {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(gameModel.backgroundImage).diskCacheKey(gameModel.backgroundImage)
                .memoryCacheKey(gameModel.backgroundImage).crossfade(true).build(),
                contentDescription = stringResource(R.string.desc_detail_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize()
                    .sharedElement(state = rememberSharedContentState(
                        key = stringResource(
                            R.string.key_image, gameModel.gameId
                        )
                    ),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 300)
                        }))
        }) {
        item {
            GameDetails(data = gameModel)
        }
    }

    LaunchedEffect(isConnected) {
        if (!isConnected) {
            snackBarHostState.showWithScope(
                coroutineScope, context.getString(R.string.error_internet_connection)
            )
        }
    }
}

@Composable
fun GameDetails(
    modifier: Modifier = Modifier,
    data: GameDetailModel,
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (data.name != null) {
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
        }
        if (data.released != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.game_release_date, data.released),
                fontSize = 14.sp,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        if (data.genres?.isNotEmpty() == true) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.game_genres, data.genres.joinToString(", ")),
                fontSize = 14.sp,
                lineHeight = 17.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        if (data.developer.isNullOrEmpty() || data.description.isNullOrEmpty()) {
            DetailPlaceholder(
                modifier = Modifier.padding(top = 20.dp)
            )
            return
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.game_developer, data.developer),
            fontSize = 14.sp,
            lineHeight = 17.sp,
            fontWeight = FontWeight.Normal,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 8.dp),
            text = stringResource(R.string.title_description),
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