package com.staygrateful.feature_list.data.source.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.staygrateful.core.source.local.database.AppDatabase
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameLocalDataSourceImpl @Inject constructor(
    override val database: AppDatabase
) : GameLocalDataSource {

    override suspend fun getCount(): Int {
        return database.dao.getCount()
    }

    override fun getItemsFlow(): Flow<List<GameEntity>> {
        return database.dao.getItemsFlow()
    }

    override suspend fun getItems(pageSize: Int, offset: Int): List<GameEntity> {
        return database.dao.getItems(pageSize, offset)
    }

    override fun getItems(): PagingSource<Int, GameEntity> {
        return database.dao.getPagingItems()
    }

    override suspend fun clearAll() {
        database.dao.clearAll()
    }

    override suspend fun insertAll(items: List<GameEntity>) {
        database.dao.insertAll(items)
    }

    override suspend fun clearAndInsertAll(items: List<GameEntity>) {
        database.withTransaction {
            database.dao.clearAll()
            database.dao.insertAll(items)
        }
    }
}
