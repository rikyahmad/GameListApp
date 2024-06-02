package com.staygrateful.feature_detail.data.source.remote

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import kotlinx.coroutines.flow.Flow

interface IDetailRemoteDataSource {
    suspend fun getDetailGame(gameId: Int): Flow<Resource<DetailGameResponse?>>
}
