package com.staygrateful.feature_detail.data.source.local

import com.staygrateful.core.source.local.entity.FavoriteEntity
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameLocalDataSource {

    suspend fun updateByGameId(gameId: Int, developer: String, description: String)

    suspend fun insertFavorite(favorite: FavoriteEntity)

    fun getFavoriteGames(): Flow<List<GameEntity>>

    suspend fun deleteFavoriteByGameId(gameId: Int)

    suspend fun isGameFavorite(gameId: Int): Boolean
}
