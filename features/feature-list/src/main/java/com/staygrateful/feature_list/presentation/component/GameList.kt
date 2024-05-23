package com.staygrateful.feature_list.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.staygrateful.core.component.InfiniteData
import com.staygrateful.core.component.InfiniteLazyColumn
import com.staygrateful.core.source.local.entity.GameEntity

@Composable
fun GameList(
    modifier: Modifier = Modifier,
    items: List<GameEntity>,
    pageSize: Int = 10,
    spacer: Dp = 15.dp,
    onBottomReached: suspend (InfiniteData) -> Unit = {},
) {
    InfiniteLazyColumn(
        pageSize = pageSize,
        modifier = modifier,
        items = items,
        onBottomReached = onBottomReached,
        spacer = spacer
    ) { _, data ->
        GameItem(data = data) {

        }
    }
}

@Composable
private fun GameItem(data: GameEntity, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick.invoke()
                }
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.backgroundImage)
                    .crossfade(true)
                    .build(),
                contentDescription = "Loaded Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
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
