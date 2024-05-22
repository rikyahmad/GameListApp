package com.staygrateful.core.network.service

import com.staygrateful.core.network.model.ApiResponse
import com.staygrateful.feature_list.data.model.Game
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(private val httpClient: HttpClient) {

    suspend fun getGames(): ApiResponse<Game> {
        return httpClient.get("https://api.rawg.io/api/games").body()
    }
}
