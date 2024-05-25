package com.staygrateful.feature_search.presentation.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.theme.ColorBackground

@Composable
fun GameListItem(index: Int, game: GameEntity, onItemClick: (GameEntity) -> Unit) {
    if(index > 0) {
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .background(Color.LightGray.copy(0.7f))
        )
    }
    Row(
        modifier = Modifier
            .background(ColorBackground)
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke(game)
            }
            .padding(horizontal = 25.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.Top
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(game.backgroundImage)
                .diskCacheKey(game.backgroundImage)
                .memoryCacheKey(game.backgroundImage)
                .crossfade(true)
                .build(),
            contentDescription = "Loaded Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(Color.LightGray)
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
        ) {
            Text(text = game.name, style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Release Date: ${game.released}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Genres: ${game.genres.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}