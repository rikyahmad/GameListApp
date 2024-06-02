package com.staygrateful.feature_favorites.data.repository

import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {

    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>>
    suspend fun deleteFavoriteByGameIds(gameIds: List<Int>)
}
