package com.staygrateful.feature_favorites.data.source.local

import androidx.paging.PagingSource
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameLocalDataSource {

    fun getFavoriteGames(): Flow<List<GameEntity>>

    //fun getPagingFavorite(): PagingSource<Int, GameEntity>
}
