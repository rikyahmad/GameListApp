package com.staygrateful.feature_list.data.source.local

import androidx.paging.PagingSource
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface IListLocalDataSource {
    suspend fun getCount(): Int

    fun getItemsFlow(): Flow<List<GameEntity>>

    suspend fun getItems(pageSize: Int, offset: Int): List<GameEntity>

    fun getItems(): PagingSource<Int, GameEntity>

    suspend fun clearAll()

    suspend fun insertAll(items: List<GameEntity>)

    suspend fun clearAndInsertAll(items: List<GameEntity>)
}
