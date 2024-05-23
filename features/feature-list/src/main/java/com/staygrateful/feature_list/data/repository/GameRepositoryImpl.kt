package com.staygrateful.feature_list.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.feature_list.data.source.local.GameLocalDataSource
import com.staygrateful.feature_list.data.source.local.GameLocalDataSourceImpl
import com.staygrateful.feature_list.data.source.mediator.GameRemoteMediator
import com.staygrateful.feature_list.data.source.remote.GameRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val localDataSource: GameLocalDataSource,
    private val remoteDataSource: GameRemoteDataSource
): GameRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagingItems(): Flow<PagingData<GameEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
            ),
            remoteMediator = GameRemoteMediator(localDataSource, remoteDataSource),
            pagingSourceFactory = { localDataSource.getItems() }
        ).flow
    }

    override suspend fun getRemoteItems(page: Int, pageSize: Int): List<GameResponse.Game> {
        return remoteDataSource.getGameList(page, pageSize)?.results ?: emptyList()
    }

    override fun getLocalItemsFlow(): Flow<List<GameEntity>> {
        return localDataSource.getItemsFlow()
    }

    override suspend fun insertLocalItems(items: List<GameEntity>) {
        localDataSource.insertAll(items)
    }

    override suspend fun insertLocalItemsAndClear(items: List<GameEntity>) {
        localDataSource.clearAndInsertAll(items)
    }
}
