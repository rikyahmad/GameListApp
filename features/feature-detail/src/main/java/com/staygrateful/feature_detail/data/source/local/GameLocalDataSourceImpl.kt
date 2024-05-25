package com.staygrateful.feature_detail.data.source.local

import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.local.repository.IDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameLocalDataSourceImpl @Inject constructor(
    private val repository: IDatabaseRepository
) : GameLocalDataSource {
    override suspend fun updateByGameId(gameId: Int, developer: String, description: String) {
        return repository.updateByGameId(gameId, developer, description)
    }

    override suspend fun insertFavorite(favorite: FavoriteGameEntity) {
        return repository.insertFavorite(favorite)
    }

    override suspend fun deleteFavoriteByGameId(gameId: Int) {
        return repository.deleteFavoriteByGameId(gameId)
    }

    override fun getFavoriteGames(): Flow<List<FavoriteGameEntity>> {
        return repository.getFavoriteGames()
    }

    override suspend fun isGameFavorite(gameId: Int): Boolean {
        return repository.isGameFavorite(gameId)
    }
}
