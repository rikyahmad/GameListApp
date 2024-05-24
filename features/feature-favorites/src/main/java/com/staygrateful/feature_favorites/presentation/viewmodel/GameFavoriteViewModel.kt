package com.staygrateful.feature_favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.feature_favorites.domain.usecase.FavoriteGameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GameFavoriteViewModel @Inject constructor(
    private val repository: FavoriteGameUsecase,
) : ViewModel() {

    val items: Flow<List<GameEntity>> = repository.getFavoriteGames()
}
