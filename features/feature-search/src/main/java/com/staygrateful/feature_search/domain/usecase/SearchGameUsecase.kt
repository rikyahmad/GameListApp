package com.staygrateful.feature_search.domain.usecase

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.SearchGameResponse
import kotlinx.coroutines.flow.Flow

interface SearchGameUsecase {
    suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>>
}