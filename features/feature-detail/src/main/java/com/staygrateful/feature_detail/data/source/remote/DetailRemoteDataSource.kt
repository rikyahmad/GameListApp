package com.staygrateful.feature_detail.data.source.remote

import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.DetailGameResponse
import com.staygrateful.core.network.remote.repository.INetworkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(
    private val iNetworkRepository: INetworkRepository
) : IDetailRemoteDataSource {

    override suspend fun getDetailGame(gameId: Int): Flow<Resource<DetailGameResponse?>> {
        return iNetworkRepository.getDetailGame(gameId)
    }
}
