package com.staygrateful.feature_list.data.source.local

import androidx.paging.PagingSource
import com.staygrateful.core.network.local.entity.GameEntity
import com.staygrateful.core.network.local.repository.IDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListLocalDataSource @Inject constructor(
    private val repository: IDatabaseRepository
) : IListLocalDataSource {

    override suspend fun getCount(): Int {
        return repository.getCount()
    }

    override fun getItemsFlow(): Flow<List<GameEntity>> {
        return repository.getItemsFlow()
    }

    override suspend fun getItems(pageSize: Int, offset: Int): List<GameEntity> {
        return repository.getItems(pageSize, offset)
    }

    override fun getItems(): PagingSource<Int, GameEntity> {
        return repository.getPagingItems()
    }

    override suspend fun clearAll() {
        repository.clearAll()
    }

    override suspend fun insertAll(items: List<GameEntity>) {
        repository.insertAll(items)
    }

    override suspend fun clearAndInsertAll(items: List<GameEntity>) {
        repository.clearAndInsertAll(items)
    }
}
