package com.staygrateful.feature_list.data.repository

import androidx.paging.PagingData
import com.staygrateful.core.network.local.entity.GameEntity
import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.GameResponse
import kotlinx.coroutines.flow.Flow

interface IListRepository {

    suspend fun getRemoteItems(page: Int, pageSize: Int): Flow<Resource<GameResponse?>>

    fun getPagingItems(): Flow<PagingData<GameEntity>>

    fun getLocalItemsFlow(): Flow<List<GameEntity>>

    suspend fun insertLocalItems(items: List<GameEntity>)

    suspend fun insertLocalItemsAndClear(items: List<GameEntity>)
}
