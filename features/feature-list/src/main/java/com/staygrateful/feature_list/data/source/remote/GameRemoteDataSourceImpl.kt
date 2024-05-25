package com.staygrateful.feature_list.data.source.remote

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.repository.INetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRemoteDataSourceImpl @Inject constructor(
    private val repository: INetworkRepository
) : GameRemoteDataSource {
    override suspend fun getRemoteItems(page: Int, pageSize: Int): Flow<Resource<GameResponse?>> {
        return repository.getGameList(page, pageSize)
    }
}
