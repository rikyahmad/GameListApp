package com.staygrateful.feature_list.data.source.mediator

import androidx.paging.*
import androidx.room.withTransaction
import com.staygrateful.core.extension.toGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.feature_list.data.source.local.GameLocalDataSource
import com.staygrateful.feature_list.data.source.remote.GameRemoteDataSource
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GameRemoteMediator @Inject constructor(
    private val localDataSource: GameLocalDataSource,
    private val remoteDataSource: GameRemoteDataSource
) : RemoteMediator<Int, GameEntity>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val response = remoteDataSource.getGameList(loadKey, state.config.pageSize)?.results

            val gameEntities = response?.map { it.toGameEntity() }
            if(!gameEntities.isNullOrEmpty()) {
                localDataSource.clearAndInsertAll(gameEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isNullOrEmpty()
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}

