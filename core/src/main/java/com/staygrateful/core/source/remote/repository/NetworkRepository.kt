package com.staygrateful.core.source.remote.repository

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.service.IApiService
import com.staygrateful.core.source.remote.mapper.ApiResponse
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.core.source.remote.model.SearchGameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiService: IApiService
): INetworkRepository, ApiResponse() {

    override suspend fun getGameList(page: Int, pageSize: Int): Flow<Resource<GameResponse?>> {
        return flow {
            emit(Resource.Loading())
            emit(safeApiCall { apiService.getGameList(page, pageSize) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailGame(gameId: Int): Flow<Resource<DetailGameResponse?>> {
        return flow {
            emit(Resource.Loading())
            emit(safeApiCall { apiService.getDetailGame(gameId) })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>> {
        return flow {
            emit(Resource.Loading())
            emit(safeApiCall { apiService.searchGames(query) })
        }.flowOn(Dispatchers.IO)
    }
}
