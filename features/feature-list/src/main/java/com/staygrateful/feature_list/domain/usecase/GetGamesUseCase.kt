package com.staygrateful.feature_list.domain.usecase

import com.staygrateful.feature_list.domain.model.GameDomainModel
import com.staygrateful.feature_list.domain.repository.GameDomainRepository
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val gameRepository: GameDomainRepository
) {
    suspend fun execute(): List<GameDomainModel> {
        return gameRepository.getGames()
    }
}

