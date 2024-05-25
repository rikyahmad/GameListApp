package com.staygrateful.feature_favorites.data.repository

import com.staygrateful.core.network.local.entity.FavoriteGameEntity
import com.staygrateful.feature_favorites.data.source.local.IFavoriteLocalDataSource
import com.staygrateful.feature_favorites.data.source.remote.IFavoriteRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val localDataSource: IFavoriteLocalDataSource,
    private val remoteDataSource: IFavoriteRemoteDataSource
) : IFavoriteRepository {

    override fun getFavoriteGames(): Flow<List<FavoriteGameEntity>> {
        return localDataSource.getFavoriteGames()
    }

    override suspend fun deleteFavoriteByGameIds(gameIds: List<Int>) {
        localDataSource.deleteFavoriteByGameIds(gameIds)
    }
}
