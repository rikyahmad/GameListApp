package com.staygrateful.feature_favorites.data.repository

import androidx.paging.PagingSource
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.feature_favorites.data.source.local.GameLocalDataSource
import com.staygrateful.feature_favorites.data.source.remote.GameRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val localDataSource: GameLocalDataSource,
    private val remoteDataSource: GameRemoteDataSource
) : GameRepository {

    override fun getFavoriteGames(): Flow<List<GameEntity>> {
        return localDataSource.getFavoriteGames()
    }
}
