package com.staygrateful.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CollapsingBar(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    safeArea: Boolean = false,
    toolbarColor: Color = Color.White.copy(0f),
    toolbarHeight: Dp = 70.dp,
    headerHeight: Dp = 300.dp,
    onScrollChange: (Float) -> Unit = {},
    contentToolbar: @Composable BoxScope.() -> Unit = {},
    contentHeader: @Composable BoxScope.() -> Unit = {},
    content: LazyListScope.() -> Unit = {},
) {

    var collapsingValue by remember {
        mutableFloatStateOf(0f)
    }

    Scaffold(
        modifier = Modifier
            .consumeWindowInsets(contentPadding)
            .windowInsetsPadding(
                WindowInsets.safeDrawing
            ),
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding),
            state = state,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled
        ) {
            item {
                Box(
                    modifier = Modifier
                        .then(
                            if (safeArea) {
                                Modifier.padding(top = toolbarHeight)
                            } else {
                                Modifier.padding(top = 0.dp)
                            }
                        )
                        .graphicsLayer {
                            /*scrolledY += state.firstVisibleItemScrollOffset - previousOffset
                            translationY = scrolledY * 0.5f
                            previousOffset = state.firstVisibleItemScrollOffset*/
                            val heightInPx =
                                if (safeArea) headerHeight.toPx() else headerHeight.toPx() - toolbarHeight.toPx()
                            val firstVisibleIndex = state.firstVisibleItemIndex
                            val offset =
                                if (firstVisibleIndex == 0) state.firstVisibleItemScrollOffset else heightInPx.toInt()
                            val value = (offset / heightInPx)
                            val distance = heightInPx.toInt() - offset
                            val translateOffsetY = (heightInPx * scaleY) - distance
                            /*scaleY = distance / heightInPx
                            scaleX = scaleY*/
                            translationY = (value * heightInPx) - (translateOffsetY / 2f)
                            collapsingValue = (1 - value).coerceIn(0f, 1f)
                            onScrollChange.invoke(collapsingValue)
                        }
                        .height(headerHeight)
                        .fillMaxWidth()
                ) {
                    contentHeader()
                }
            }
            content()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(toolbarHeight)
                .background(toolbarColor.copy(if (safeArea) 1f else (1 - collapsingValue)))
        ) {
            contentToolbar()
        }
    }
}
