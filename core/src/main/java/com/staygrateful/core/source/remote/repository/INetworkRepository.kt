package com.staygrateful.core.source.remote.repository

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface INetworkRepository {
    suspend fun getGames(): Flow<Resource<GameResponse>>
}
