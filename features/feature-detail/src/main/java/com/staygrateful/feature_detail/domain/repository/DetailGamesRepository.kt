package com.staygrateful.feature_detail.domain.repository

import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.feature_detail.data.repository.GameRepository
import com.staygrateful.feature_detail.domain.usecase.DetailGameUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailGamesRepository @Inject constructor(
    private val repository: GameRepository
) : DetailGameUsecase {
    override suspend fun insertFavorite(favorite: FavoriteGameEntity) {
        return repository.insertFavorite(favorite)
    }

    override suspend fun getDetailGame(gameId: Int): Flow<Resource<DetailGameResponse?>> {
        return repository.getDetailGame(gameId)
    }

    override fun getFavoriteGames(): Flow<List<FavoriteGameEntity>> {
        return repository.getFavoriteGames()
    }

    override suspend fun deleteFavoriteByGameId(gameId: Int) {
        return repository.deleteFavoriteByGameId(gameId)
    }

    override suspend fun isGameFavorite(gameId: Int): Boolean {
        return repository.isGameFavorite(gameId)
    }

    override suspend fun updateByGameId(gameId: Int, developer: String, description: String) {
        return repository.updateByGameId(gameId, developer, description)
    }
}

