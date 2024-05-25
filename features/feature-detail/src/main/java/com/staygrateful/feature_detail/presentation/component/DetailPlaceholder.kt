package com.staygrateful.feature_detail.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DetailPlaceholder(
    modifier: Modifier = Modifier,
    itemHeight: Dp = 20.dp,
    color: Color = Color.LightGray,
    shape: Shape = RectangleShape
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(itemHeight)
                .background(color, shape)
        )
        Box(
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
                .height(itemHeight)
                .background(color, shape)
        )
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
                    .background(color, shape)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(itemHeight)
                .background(color, shape)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(itemHeight)
                .background(color, shape)
        )
    }
}