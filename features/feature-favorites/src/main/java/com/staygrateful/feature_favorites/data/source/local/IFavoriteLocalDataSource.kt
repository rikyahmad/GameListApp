package com.staygrateful.feature_favorites.data.source.local

import com.staygrateful.core.network.local.entity.FavoriteGameEntity
import kotlinx.coroutines.flow.Flow

interface IFavoriteLocalDataSource {

    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>>
    suspend fun deleteFavoriteByGameIds(gameIds: List<Int>)
}
