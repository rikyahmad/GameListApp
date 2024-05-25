package com.staygrateful.feature_list.domain.repository

import androidx.paging.PagingData
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.feature_list.data.repository.GameRepository
import com.staygrateful.feature_list.domain.usecase.GetGameUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesRepository @Inject constructor(
    private val repository: GameRepository
): GetGameUsecase {

    override fun getGames(): Flow<PagingData<GameEntity>> {
        return repository.getPagingItems()
    }

    override fun getItemsFlow(): Flow<List<GameEntity>> {
        return repository.getLocalItemsFlow()
    }

    override suspend fun getRemoteItems(page: Int, pageSize: Int): Flow<Resource<GameResponse?>> {
        return repository.getRemoteItems(page, pageSize)
    }

    override suspend fun insertItemsToDb(items: List<GameEntity>) {
        repository.insertLocalItems(items)
    }

    override suspend fun clearAndInsertAll(items: List<GameEntity>) {
        repository.insertLocalItemsAndClear(items)
    }
}

