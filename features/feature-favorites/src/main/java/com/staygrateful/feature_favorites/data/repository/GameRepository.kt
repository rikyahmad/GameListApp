package com.staygrateful.feature_favorites.data.repository

import androidx.paging.PagingSource
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface GameRepository {

    fun getFavoriteGames(): Flow<List<GameEntity>>

    //fun getPagingFavorite(): PagingSource<Int, GameEntity>
}
