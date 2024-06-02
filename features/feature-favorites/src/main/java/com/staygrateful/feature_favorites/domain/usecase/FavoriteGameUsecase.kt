package com.staygrateful.feature_favorites.domain.usecase

import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteGameUsecase {
    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>>
    suspend fun deleteFavoriteByGameIds(gameIds: List<Int>)
}