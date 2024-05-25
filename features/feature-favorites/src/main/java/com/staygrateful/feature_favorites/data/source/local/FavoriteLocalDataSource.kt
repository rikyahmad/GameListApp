package com.staygrateful.feature_favorites.data.source.local

import com.staygrateful.core.network.local.entity.FavoriteGameEntity
import com.staygrateful.core.network.local.repository.IDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteLocalDataSource @Inject constructor(
    private val repository: IDatabaseRepository
) : IFavoriteLocalDataSource {

    override fun getFavoriteGames(): Flow<List<FavoriteGameEntity>> {
        return repository.getFavoriteGames()
    }

    override suspend fun deleteFavoriteByGameIds(gameIds: List<Int>) {
        repository.deleteFavoritesByGameIds(gameIds)
    }
}
