package com.staygrateful.feature_search.data.source.remote

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.SearchGameResponse
import kotlinx.coroutines.flow.Flow

interface GameRemoteDataSource {
    suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>>
}
