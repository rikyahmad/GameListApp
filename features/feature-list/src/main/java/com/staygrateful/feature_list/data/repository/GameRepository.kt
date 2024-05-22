package com.staygrateful.feature_list.data.repository

import com.staygrateful.core.network.model.ApiResponse
import com.staygrateful.core.network.service.ApiService
import com.staygrateful.feature_list.data.model.Game
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getGames(): ApiResponse<Game> {
        return apiService.getGames()
    }
}
