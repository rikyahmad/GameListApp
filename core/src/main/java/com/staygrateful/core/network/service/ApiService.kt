package com.staygrateful.core.network.service

import com.staygrateful.core.network.model.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiService(private val httpClient: HttpClient) {

    /*suspend fun getGames(): ApiResponse<Game> {
        return httpClient.get("https://api.rawg.io/api/games")
    }*/
}
