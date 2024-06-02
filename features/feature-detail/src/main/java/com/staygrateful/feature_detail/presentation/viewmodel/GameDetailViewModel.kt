package com.staygrateful.feature_detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staygrateful.core.helper.INetworkMonitor
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.feature_detail.domain.model.GameDetailModel
import com.staygrateful.feature_detail.domain.usecase.DetailGameUsecase
import com.staygrateful.feature_detail.external.extension.toFavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val repository: DetailGameUsecase,
    private val networkMonitor: INetworkMonitor
) : ViewModel() {

    private val _gameDetail = MutableStateFlow<Resource<DetailGameResponse?>>(Resource.Loading())
    val gameDetail: StateFlow<Resource<DetailGameResponse?>> = _gameDetail
    val isConnected: StateFlow<Boolean> = networkMonitor.isConnected

    fun getDetailGame(gameId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDetailGame(gameId).collect { resource ->
                _gameDetail.value = resource
                updateDescriptionByGameId(
                    gameId,
                    resource.data?.developersName,
                    resource.data?.description
                ) {}
            }
        }
    }

    fun updateFavoriteStatus(data: GameDetailModel, isFavorite: Boolean, onResult: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isFavorite) {
                repository.insertFavorite(data.toFavoriteEntity())
            } else {
                repository.deleteFavoriteByGameId(data.gameId)
            }
            onResult.invoke()
        }
    }

    fun isFavorite(gameId: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.isGameFavorite(gameId)
            onResult.invoke(result)
        }
    }

    private fun updateDescriptionByGameId(
        gameId: Int,
        developer: String?,
        description: String?,
        onResult: () -> Unit
    ) {
        if (developer == null) return
        if (description == null) return
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateByGameId(gameId, developer, description)
            onResult.invoke()
        }
    }
}
