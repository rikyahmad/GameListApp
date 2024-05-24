package com.staygrateful.core.source.local.repository

import androidx.paging.PagingSource
import com.staygrateful.core.source.local.entity.FavoriteEntity
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface IDatabaseRepository {

    suspend fun insertFavorite(favorite: FavoriteEntity)
    fun getFavoriteGames(): Flow<List<GameEntity>>
    suspend fun deleteFavoriteByGameId(gameId: Int)
    suspend fun isGameFavorite(gameId: Int): Boolean
    fun getItemsFlow(): Flow<List<GameEntity>>
    fun getPagingItems(): PagingSource<Int, GameEntity>
    suspend fun updateByGameId(gameId: Int, developer: String, description: String)
    suspend fun getItems(pageSize: Int, offset: Int): List<GameEntity>
    fun getItem(gameId: Int): GameEntity?
    suspend fun upsertAll(games: List<GameEntity>)
    suspend fun insertAll(games: List<GameEntity>)
    suspend fun insert(game: GameEntity)
    suspend fun clearAll()
    suspend fun getCount(): Int
    suspend fun clearAndInsertAll(items: List<GameEntity>)
}
