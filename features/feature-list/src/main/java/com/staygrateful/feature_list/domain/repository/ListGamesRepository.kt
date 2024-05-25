package com.staygrateful.feature_list.domain.repository

import androidx.paging.PagingData
import com.staygrateful.core.network.local.entity.GameEntity
import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.GameResponse
import com.staygrateful.feature_list.data.repository.IListRepository
import com.staygrateful.feature_list.domain.usecase.ListGameUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListGamesRepository @Inject constructor(
    private val repository: IListRepository
): ListGameUsecase {

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

