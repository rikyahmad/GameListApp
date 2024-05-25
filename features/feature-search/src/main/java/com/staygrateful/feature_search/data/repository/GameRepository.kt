package com.staygrateful.feature_search.data.repository

import androidx.paging.PagingData
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.model.SearchGameResponse
import kotlinx.coroutines.flow.Flow

interface GameRepository  {

    suspend fun searchGames(query: String): Flow<Resource<SearchGameResponse?>>
}
