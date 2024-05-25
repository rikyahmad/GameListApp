package com.staygrateful.feature_search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staygrateful.core.extension.toGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.model.SearchGameResponse
import com.staygrateful.feature_search.domain.usecase.SearchGameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameSearchViewModel @Inject constructor(
    private val repository: SearchGameUsecase,
) : ViewModel() {

    private val _result = MutableStateFlow<List<GameEntity>>(emptyList())
    val result: StateFlow<List<GameEntity>> get() = _result

    fun searchGames(query: String) {
        viewModelScope.launch {
            repository.searchGames(query).map {
                it.data?.results?.map { result -> result.toGameEntity() }
            }.collectLatest {
                _result.value = it.orEmpty()
            }
        }
    }
}
