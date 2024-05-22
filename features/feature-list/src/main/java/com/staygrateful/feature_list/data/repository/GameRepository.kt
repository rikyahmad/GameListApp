package com.staygrateful.feature_list.data.repository

import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.core.utils.Resource
import com.staygrateful.feature_list.domain.repository.GameDomainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val repository: INetworkRepository
) : GameDomainRepository {

    override suspend fun getGames(): Flow<Resource<GameResponse>> {
        return repository.getGames()
    }
}
