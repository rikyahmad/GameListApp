package com.staygrateful.feature_list.domain.repository

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GameDomainRepository {
    suspend fun getGames(): Flow<Resource<GameResponse>>
}