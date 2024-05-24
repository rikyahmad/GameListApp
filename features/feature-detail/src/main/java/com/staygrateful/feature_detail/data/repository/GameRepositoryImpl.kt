package com.staygrateful.feature_detail.data.repository

import com.staygrateful.core.source.local.entity.FavoriteEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.feature_detail.data.source.local.GameLocalDataSource
import com.staygrateful.feature_detail.data.source.remote.GameRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val localDataSource: GameLocalDataSource,
    private val remoteDataSource: GameRemoteDataSource
) : GameRepository {
    override suspend fun updateByGameId(gameId: Int, developer: String, description: String) {
        return localDataSource.updateByGameId(gameId, developer, description)
    }

    override suspend fun insertFavorite(favorite: FavoriteEntity) {
        return localDataSource.insertFavorite(favorite)
    }

    override fun getFavoriteGames(): Flow<List<GameEntity>> {
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
