package com.staygrateful.feature_search.data.source.remote

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.SearchGameResponse
import com.staygrateful.core.source.remote.repository.INetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val repository: INetworkRepository,
) : ISearchRemoteDataSource {

    override suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>> {
        return repository.searchGames(query)
    }
}
