package com.staygrateful.feature_detail.data.repository

import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.feature_detail.data.source.local.IDetailLocalDataSource
import com.staygrateful.feature_detail.data.source.remote.IDetailRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val localDataSource: IDetailLocalDataSource,
    private val remoteDataSource: IDetailRemoteDataSource
) : IDetailRepository {
    override suspend fun updateByGameId(gameId: Int, developer: String, description: String) {
        return localDataSource.updateByGameId(gameId, developer, description)
    }

    override suspend fun insertFavorite(favorite: FavoriteGameEntity) {
        return localDataSource.insertFavorite(favorite)
    }

    override fun getFavoriteGames(): Flow<List<FavoriteGameEntity>> {
        return localDataSource.getFavoriteGames()
    }

    override suspend fun deleteFavoriteByGameId(gameId: Int) {
        return localDataSource.deleteFavoriteByGameId(gameId)
    }

    override suspend fun isGameFavorite(gameId: Int): Boolean {
        return localDataSource.isGameFavorite(gameId)
    }

    override suspend fun getDetailGame(gameId: Int): Flow<Resource<DetailGameResponse?>> {
        return remoteDataSource.getDetailGame(gameId)
    }
}
