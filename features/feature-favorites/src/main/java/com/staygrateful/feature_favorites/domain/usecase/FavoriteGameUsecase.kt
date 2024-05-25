package com.staygrateful.feature_favorites.domain.usecase

import androidx.paging.PagingSource
import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteGameUsecase {
    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>>
    //fun getPagingFavorite(): PagingSource<Int, GameEntity>
}