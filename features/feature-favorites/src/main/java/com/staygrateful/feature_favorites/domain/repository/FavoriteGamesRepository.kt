package com.staygrateful.feature_favorites.domain.repository

import com.staygrateful.core.network.local.entity.FavoriteGameEntity
import com.staygrateful.feature_favorites.data.repository.IFavoriteRepository
import com.staygrateful.feature_favorites.domain.usecase.FavoriteGameUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteGamesRepository @Inject constructor(
    private val repository: IFavoriteRepository
) : FavoriteGameUsecase {
    override fun getFavoriteGames(): Flow<List<FavoriteGameEntity>> {
        return repository.getFavoriteGames()
    }

    override suspend fun deleteFavoriteByGameIds(gameIds: List<Int>) {
        repository.deleteFavoriteByGameIds(gameIds)
    }
}

