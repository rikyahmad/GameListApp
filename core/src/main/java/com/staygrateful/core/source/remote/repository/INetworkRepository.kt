package com.staygrateful.core.source.remote.repository

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.model.SearchGameResponse
import kotlinx.coroutines.flow.Flow

interface INetworkRepository {
    suspend fun getGameList(page: Int, pageSize: Int): Flow<Resource<GameResponse?>>
    suspend fun getDetailGame(gameId: Int): Flow<Resource<DetailGameResponse?>>
    suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>>
}
