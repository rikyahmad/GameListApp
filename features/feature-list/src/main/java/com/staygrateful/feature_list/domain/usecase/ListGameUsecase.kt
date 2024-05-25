package com.staygrateful.feature_list.domain.usecase

import androidx.paging.PagingData
import com.staygrateful.core.network.local.entity.GameEntity
import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.GameResponse
import kotlinx.coroutines.flow.Flow

interface ListGameUsecase {
    fun getGames(): Flow<PagingData<GameEntity>>
    fun getItemsFlow(): Flow<List<GameEntity>>
    suspend fun getRemoteItems(page: Int, pageSize: Int): Flow<Resource<GameResponse?>>
    suspend fun insertItemsToDb(items: List<GameEntity>)
    suspend fun clearAndInsertAll(items: List<GameEntity>)
}