package com.staygrateful.core.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@Composable
fun <T> InfiniteLazyColumn(
    pageSize: Int = 10,
    modifier: Modifier,
    items: List<T>,
    spacer: Dp = 15.dp,
    initialLoad: Boolean = false,
    enablePlaceholder: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onBottomReached: suspend (InfiniteData) -> Unit,
    placeholderContent: @Composable () -> Unit = {},
    itemContent: @Composable (Int, T) -> Unit,
) {
    val listState = rememberLazyListState()
    var isLoading by remember { mutableStateOf(false) }

    Box {
        if (isLoading && items.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                if(!enablePlaceholder) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(29.dp)
                    )
                } else {
                    placeholderContent()
                }
            }
        } else {
            LazyColumn(
                modifier = modifier,
                state = listState,
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(spacer),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items.size) { index ->
                    itemContent(index, items[index])
                }
                if (isLoading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(vertical = 20.dp)
                                .size(25.dp)
                        )
                    }
                }
            }
        }
    }

    if(initialLoad) {
        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                if (isLoading) return@withContext
                isLoading = true
                onProgressState {
                    onBottomReached.invoke(InfiniteData(1, pageSize, InfiniteState.Refresh))
                }
                isLoading = false
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .map { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull()
                val itemCount = listState.layoutInfo.totalItemsCount
                itemCount > 0 && (lastVisibleItem?.index == itemCount - 1)
            }
            .distinctUntilChanged()
            .collect { isAtEnd ->
                if (isAtEnd) {
                    withContext(Dispatchers.IO) {
                        if (!isLoading) {
                            isLoading = true
                            onProgressState {
                                onBottomReached.invoke(
                                    InfiniteData(
                                        (listState.layoutInfo.totalItemsCount / pageSize) + 1,
                                        pageSize,
                                        InfiniteState.Append
                                    )
                                )
                            }
                            isLoading = false
                        }
                    }
                }
            }
    }
}

private suspend fun onProgressState(onProgress: suspend () -> Unit) {
    try {
        onProgress.invoke()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    delay(1000)
}

data class InfiniteData(
    val nextPage: Int,
    val pageSize: Int,
    val state: InfiniteState,
)

enum class InfiniteState {
    Refresh,
    Append,
}