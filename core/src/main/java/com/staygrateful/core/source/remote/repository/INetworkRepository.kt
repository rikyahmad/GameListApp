package com.staygrateful.core.source.remote.repository

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.mapper.Resource
import kotlinx.coroutines.flow.Flow

interface INetworkRepository {
    suspend fun getGames(page: Int, pageSize: Int): Flow<Resource<GameResponse?>>
}
