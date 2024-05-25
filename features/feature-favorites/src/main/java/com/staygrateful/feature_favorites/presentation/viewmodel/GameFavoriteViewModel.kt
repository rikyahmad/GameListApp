package com.staygrateful.feature_favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.staygrateful.core.extension.toGameEntity
import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.feature_favorites.domain.usecase.FavoriteGameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GameFavoriteViewModel @Inject constructor(
    private val repository: FavoriteGameUsecase,
) : ViewModel() {

    private val favoriteItems: Flow<List<FavoriteGameEntity>> = repository.getFavoriteGames()

    val items: Flow<List<GameEntity>> = favoriteItems.map { favoriteGames ->
        favoriteGames.map { favoriteGame ->
            favoriteGame.toGameEntity()
        }
    }
}
