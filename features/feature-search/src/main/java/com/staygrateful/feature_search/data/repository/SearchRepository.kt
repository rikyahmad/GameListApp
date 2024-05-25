package com.staygrateful.feature_search.data.repository

import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.SearchGameResponse
import com.staygrateful.feature_search.data.source.local.ISearchLocalDataSource
import com.staygrateful.feature_search.data.source.remote.ISearchRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val localDataSource: ISearchLocalDataSource,
    private val remoteDataSource: ISearchRemoteDataSource
) : ISearchRepository {
    override suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>> {
        return remoteDataSource.searchGames(query)
    }
}
