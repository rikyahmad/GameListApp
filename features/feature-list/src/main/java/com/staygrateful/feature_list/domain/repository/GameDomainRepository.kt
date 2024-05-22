package com.staygrateful.feature_list.domain.repository

import com.staygrateful.feature_list.domain.model.GameDomainModel

interface GameDomainRepository {
    suspend fun getGames(): List<GameDomainModel>
}