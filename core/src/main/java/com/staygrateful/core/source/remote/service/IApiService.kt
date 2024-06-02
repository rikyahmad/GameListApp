package com.staygrateful.core.source.remote.service

import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.model.SearchGameResponse

interface IApiService {
    suspend fun getGameList(page: Int, pageSize: Int): GameResponse?
    suspend fun getDetailGame(gameId: Int): DetailGameResponse?
    suspend fun searchGames(query: String): SearchGameResponse?
}