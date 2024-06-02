package com.staygrateful.feature_list.data.source.remote

import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.GameResponse
import kotlinx.coroutines.flow.Flow

interface IListRemoteDataSource {
    suspend fun getRemoteItems(page: Int, pageSize: Int): Flow<Resource<GameResponse?>>
}
