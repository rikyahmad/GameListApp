package com.staygrateful.feature_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.feature_list.domain.repository.GetGamesRepository
import com.staygrateful.feature_list.domain.usecase.GetGameUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val repository: GetGameUsecase,
) : ViewModel() {

    val items: Flow<List<GameEntity>> = repository.getItemsFlow()

    suspend fun collect(page: Int, pageSize: Int, clearCache: Boolean) {
        println("Collect data at page : $page")
        val result = repository.getRemoteItems(page, pageSize)
        if(result.isNotEmpty()) {
            val mapList = result.map { GameEntity.from(it)}
            if(clearCache) {
                repository.clearAndInsertAll(mapList)
            } else {
                repository.insertItemsToDb(mapList)
            }
        }
    }
}
