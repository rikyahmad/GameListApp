package com.staygrateful.feature_favorites.domain.repository

import androidx.paging.PagingSource
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.feature_favorites.data.repository.GameRepository
import com.staygrateful.feature_favorites.domain.usecase.FavoriteGameUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteGamesRepository @Inject constructor(
    private val repository: GameRepository
) : FavoriteGameUsecase {
    override fun getFavoriteGames(): Flow<List<GameEntity>> {
        return repository.getFavoriteGames()
    }
}

