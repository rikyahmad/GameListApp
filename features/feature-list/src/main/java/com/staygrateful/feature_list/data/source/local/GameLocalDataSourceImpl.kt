package com.staygrateful.feature_list.data.source.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.staygrateful.core.source.local.database.AppDatabase
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.local.repository.DatabaseRepository
import com.staygrateful.core.source.local.repository.IDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameLocalDataSourceImpl @Inject constructor(
    private val repository: IDatabaseRepository
) : GameLocalDataSource {

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
