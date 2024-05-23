package com.staygrateful.core.source.remote.service

import com.staygrateful.core.source.remote.model.GameResponse

interface IApiService {
    suspend fun getGameList(page: Int, pageSize: Int): GameResponse?
}