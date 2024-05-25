package com.staygrateful.feature_search.data.repository

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.SearchGameResponse
import com.staygrateful.feature_search.data.source.local.GameLocalDataSource
import com.staygrateful.feature_search.data.source.remote.GameRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val localDataSource: GameLocalDataSource,
    private val remoteDataSource: GameRemoteDataSource
) : GameRepository {
    override suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>> {
        return remoteDataSource.searchGames(query)
    }
}
