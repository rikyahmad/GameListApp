package com.staygrateful.core.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.staygrateful.core.source.local.entity.GameEntity

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.GameItem(
    data: GameEntity,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (GameEntity) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 7.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick.invoke(data)
                }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.backgroundImage)
                    .diskCacheKey(data.backgroundImage)
                    .memoryCacheKey(data.backgroundImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "Loaded Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .aspectRatio(6 / 3f)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${data.gameId}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 300)
                        }
                    )
            )
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = data.name,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "Release Date: ${data.released}",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                )
                Text(
                    text = "Genres: ${data.genres.joinToString(", ")}",
                    fontSize = 13.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                )
            }
        }
    }
}