package com.staygrateful.feature_detail.domain.usecase

import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import kotlinx.coroutines.flow.Flow

interface DetailGameUsecase {
    suspend fun insertFavorite(favorite: FavoriteGameEntity)

    suspend fun getDetailGame(gameId: Int): Flow<Resource<DetailGameResponse?>>

    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>>

    suspend fun deleteFavoriteByGameId(gameId: Int)

    suspend fun isGameFavorite(gameId: Int): Boolean

    suspend fun updateByGameId(gameId: Int, developer: String, description: String)

}