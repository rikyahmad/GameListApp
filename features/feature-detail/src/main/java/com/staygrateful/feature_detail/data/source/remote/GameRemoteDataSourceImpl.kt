package com.staygrateful.feature_detail.data.source.remote

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.core.source.remote.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GameRemoteDataSourceImpl @Inject constructor(
    private val iNetworkRepository: INetworkRepository
) : GameRemoteDataSource {

    override suspend fun getDetailGame(gameId: Int): Flow<Resource<DetailGameResponse?>> {
        return iNetworkRepository.getDetailGame(gameId)
    }
}
