package com.staygrateful.feature_favorites.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staygrateful.core.extension.toGameEntity
import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.feature_favorites.domain.usecase.FavoriteGameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameFavoriteViewModel @Inject constructor(
    private val repository: FavoriteGameUsecase,
) : ViewModel() {

    private val favoriteItems: Flow<List<FavoriteGameEntity>> = repository.getFavoriteGames()

    val selectedItems = mutableStateListOf<GameEntity>()

    val items: Flow<List<GameEntity>> = favoriteItems.map { favoriteGames ->
        favoriteGames.map { favoriteGame ->
            favoriteGame.toGameEntity()
        }
    }

    fun deleteItems(selectedItems: List<GameEntity>) {
        if(selectedItems.isEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteByGameIds(selectedItems.map { it.gameId })
        }
    }

    fun onSelectedUpdate(data: GameEntity, selected: Boolean) {
        if (selected) {
            selectedItems.add(data)
        } else {
            selectedItems.remove(data)
        }
    }
}
