package com.staygrateful.feature_detail.data.source.local

import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameLocalDataSource {

    suspend fun updateByGameId(gameId: Int, developer: String, description: String)

    suspend fun insertFavorite(favorite: FavoriteGameEntity)

    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>>

    suspend fun deleteFavoriteByGameId(gameId: Int)

    suspend fun isGameFavorite(gameId: Int): Boolean
}
