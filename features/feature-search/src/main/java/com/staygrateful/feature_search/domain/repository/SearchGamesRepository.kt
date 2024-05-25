package com.staygrateful.feature_search.domain.repository

import androidx.paging.PagingData
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.model.SearchGameResponse
import com.staygrateful.feature_search.data.repository.GameRepository
import com.staygrateful.feature_search.domain.usecase.SearchGameUsecase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchGamesRepository @Inject constructor(
    private val repository: GameRepository
): SearchGameUsecase {

    override suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>> {
        return repository.searchGames(query)
    }
}

