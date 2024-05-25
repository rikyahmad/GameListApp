package com.staygrateful.feature_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staygrateful.core.extension.toGameEntity
import com.staygrateful.core.helper.INetworkMonitor
import com.staygrateful.core.network.local.entity.GameEntity
import com.staygrateful.core.network.remote.mapper.Resource
import com.staygrateful.core.network.remote.model.GameResponse
import com.staygrateful.feature_list.domain.usecase.ListGameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val repository: ListGameUsecase,
    private val networkMonitor: INetworkMonitor
) : ViewModel() {

    val items: Flow<List<GameEntity>> = repository.getItemsFlow()

    private val _result = MutableStateFlow<Resource<GameResponse?>>(Resource.Loading())
    val result: StateFlow<Resource<GameResponse?>> get() = _result

    val isConnected: StateFlow<Boolean> = networkMonitor.isConnected

    suspend fun collect(
        page: Int,
        pageSize: Int,
        clearCache: Boolean,
    ) {
        repository.getRemoteItems(page, pageSize).collect { result ->
            _result.value = result
            when (result) {
                is Resource.Success -> {
                    val data = result.data?.results.orEmpty()
                    if (data.isNotEmpty()) {
                        val mapList = data.map { it.toGameEntity() }
                        if (clearCache) {
                            repository.clearAndInsertAll(mapList)
                        } else {
                            repository.insertItemsToDb(mapList)
                        }
                    }
                }

                is Resource.Loading -> {}

                is Resource.Error -> {}

                is Resource.None -> {}
            }
        }
    }

    fun stopMonitoring() {
        viewModelScope.launch {
            networkMonitor.stopMonitoring()
        }
    }
}
