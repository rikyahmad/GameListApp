package com.staygrateful.feature_search.domain.repository

import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.SearchGameResponse
import com.staygrateful.feature_search.data.repository.ISearchRepository
import com.staygrateful.feature_search.domain.usecase.SearchGameUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchGamesRepository @Inject constructor(
    private val repository: ISearchRepository
): SearchGameUsecase {

    override suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>> {
        return repository.searchGames(query)
    }
}

