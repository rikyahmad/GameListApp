package com.staygrateful.feature_search.data.source.remote

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.model.SearchGameResponse
import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.core.source.remote.repository.NetworkRepository
import com.staygrateful.core.source.remote.service.ApiService
import com.staygrateful.feature_search.data.repository.GameRepository
import com.staygrateful.feature_search.data.source.remote.GameRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRemoteDataSourceImpl @Inject constructor(
    private val repository: INetworkRepository,
) : GameRemoteDataSource {

    override suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>> {
        return repository.searchGames(query)
    }
}
