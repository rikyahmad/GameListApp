package com.staygrateful.feature_list.domain.usecase

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.utils.Resource
import com.staygrateful.feature_list.data.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend fun getGames(): Flow<Resource<GameResponse>> {
        return repository.getGames()
    }
}

