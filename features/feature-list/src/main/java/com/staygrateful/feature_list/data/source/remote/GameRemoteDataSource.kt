package com.staygrateful.feature_list.data.source.remote

import com.staygrateful.core.source.remote.model.GameResponse

interface GameRemoteDataSource {
    suspend fun getGameList(page: Int, pageSize: Int): GameResponse?
}
