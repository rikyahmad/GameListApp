package com.staygrateful.feature_list.data.source.remote

import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.GameResponse
import com.staygrateful.core.network.remote.repository.INetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListRemoteDataSource @Inject constructor(
    private val repository: INetworkRepository
) : IListRemoteDataSource {
    override suspend fun getRemoteItems(page: Int, pageSize: Int): Flow<Resource<GameResponse?>> {
        return repository.getGameList(page, pageSize)
    }
}
