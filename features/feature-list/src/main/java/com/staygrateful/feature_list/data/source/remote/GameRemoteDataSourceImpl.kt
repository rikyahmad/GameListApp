package com.staygrateful.feature_list.data.source.remote

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.service.ApiService
import javax.inject.Inject

class GameRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : GameRemoteDataSource {
    override suspend fun getGameList(page: Int, pageSize: Int): GameResponse? {
        return apiService.getGameList(page, pageSize)
    }
}
