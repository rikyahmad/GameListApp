package com.staygrateful.core.network.remote.service

import com.staygrateful.core.network.remote.model.DetailGameResponse
import com.staygrateful.core.network.remote.model.GameResponse
import com.staygrateful.core.network.remote.model.SearchGameResponse

interface IApiService {
    suspend fun getGameList(page: Int, pageSize: Int): GameResponse?
    suspend fun getDetailGame(gameId: Int): DetailGameResponse?
    suspend fun searchGames(query: String): SearchGameResponse?
}