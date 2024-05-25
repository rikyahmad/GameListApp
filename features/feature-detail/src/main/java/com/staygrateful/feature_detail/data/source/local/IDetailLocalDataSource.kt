package com.staygrateful.feature_detail.data.source.local

import com.staygrateful.core.network.local.entity.FavoriteGameEntity
import kotlinx.coroutines.flow.Flow

interface IDetailLocalDataSource {

    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>>

    suspend fun updateByGameId(gameId: Int, developer: String, description: String)

    suspend fun insertFavorite(favorite: FavoriteGameEntity)

    suspend fun deleteFavoriteByGameId(gameId: Int)

    suspend fun isGameFavorite(gameId: Int): Boolean
}
