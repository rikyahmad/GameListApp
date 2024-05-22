package com.staygrateful.feature_list.data.source.remote

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.service.ApiService
import javax.inject.Inject

class GameRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getGames(): GameResponse? {
        return apiService.getGames()
    }
}
