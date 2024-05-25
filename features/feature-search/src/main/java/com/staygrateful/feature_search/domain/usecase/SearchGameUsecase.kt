package com.staygrateful.feature_search.domain.usecase

import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.SearchGameResponse
import kotlinx.coroutines.flow.Flow

interface SearchGameUsecase {
    suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>>
}