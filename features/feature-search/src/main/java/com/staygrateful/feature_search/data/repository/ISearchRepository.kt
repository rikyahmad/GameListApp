package com.staygrateful.feature_search.data.repository

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.SearchGameResponse
import kotlinx.coroutines.flow.Flow

interface ISearchRepository  {

    suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>>
}
