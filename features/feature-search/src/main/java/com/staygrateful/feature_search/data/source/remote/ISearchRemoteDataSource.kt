package com.staygrateful.feature_search.data.source.remote

import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.SearchGameResponse
import kotlinx.coroutines.flow.Flow

interface ISearchRemoteDataSource {
    suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>>
}
