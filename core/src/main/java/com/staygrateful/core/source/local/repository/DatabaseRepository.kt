package com.staygrateful.core.source.local.repository

import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.staygrateful.core.extension.merge
import com.staygrateful.core.source.local.database.AppDatabase
import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val appDatabase: AppDatabase
) : IDatabaseRepository {
    override suspend fun insertFavorite(favorite: FavoriteGameEntity) {
        appDatabase.dao.insertFavorite(favorite)
    }

    override fun getFavoriteGames(): Flow<List<FavoriteGameEntity>> {
        return appDatabase.dao.getFavoriteGames()
    }

    override suspend fun deleteFavoriteByGameId(gameId: Int) {
        return appDatabase.dao.deleteFavoriteByGameId(gameId)
    }

    override suspend fun isGameFavorite(gameId: Int): Boolean {
        return appDatabase.dao.isGameFavorite(gameId)
    }

    override fun getItemsFlow(): Flow<List<GameEntity>> {
        return appDatabase.dao.getItemsFlow()
    }

    override fun getPagingItems(): PagingSource<Int, GameEntity> {
        return appDatabase.dao.getPagingItems()
    }

    override suspend fun updateByGameId(gameId: Int, developer: String, description: String) {
        appDatabase.dao.updateByGameId(gameId, developer, description)
    }

    override suspend fun getItems(pageSize: Int, offset: Int): List<GameEntity> {
        return appDatabase.dao.getItems(pageSize, offset)
    }

    override fun getItem(gameId: Int): GameEntity? {
        return appDatabase.dao.getItem(gameId)
    }

    override suspend fun upsertAll(games: List<GameEntity>) {
        appDatabase.dao.upsertAll(games)
    }

    override suspend fun insertAll(games: List<GameEntity>) {
        appDatabase.withTransaction {
            for (game in games) {
                val result = appDatabase.dao.getItem(game.id)
                if (result != null) {
                    appDatabase.dao.insert(game.merge(result))
                } else {
                    appDatabase.dao.insert(game)
                }
            }
        }
    }

    override suspend fun insert(game: GameEntity) {
        appDatabase.dao.insert(game)
    }

    override suspend fun clearAll() {
        appDatabase.dao.clearAll()
    }

    override suspend fun getCount(): Int {
        return appDatabase.dao.getCount()
    }

    override suspend fun clearAndInsertAll(items: List<GameEntity>) {
        appDatabase.withTransaction {
            appDatabase.dao.clearAll()
            appDatabase.dao.insertAll(items)
        }
    }
}
