package com.staygrateful.feature_list.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.feature_list.data.source.local.IListLocalDataSource
import com.staygrateful.feature_list.data.source.remote.IListRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListRepository @Inject constructor(
    private val localDataSource: IListLocalDataSource,
    private val remoteDataSource: IListRemoteDataSource
) : IListRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPagingItems(): Flow<PagingData<GameEntity>> {
        return Pager(config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10,
        ), pagingSourceFactory = { localDataSource.getItems() }).flow
    }

    override suspend fun getRemoteItems(page: Int, pageSize: Int): Flow<Resource<GameResponse?>> {
        return remoteDataSource.getRemoteItems(page, pageSize)
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
