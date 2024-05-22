package com.staygrateful.core.source.remote.repository

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.service.IApiService
import com.staygrateful.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiService: IApiService
): INetworkRepository {

    override suspend fun getGames(): Flow<Resource<GameResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = apiService.getGames()
                if(response != null) {
                    emit(Resource.Success(response))
                } else {
                    throw Exception("Response is null")
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }
    }
}
