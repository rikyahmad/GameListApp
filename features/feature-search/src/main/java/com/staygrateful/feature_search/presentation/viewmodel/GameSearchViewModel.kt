package com.staygrateful.feature_search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staygrateful.core.source.remote.mapper.Resource
import com.staygrateful.core.source.remote.model.SearchGameResponse
import com.staygrateful.core.utils.DelayUtil
import com.staygrateful.core.utils.TimberLog
import com.staygrateful.feature_search.domain.model.GameSearchModel
import com.staygrateful.feature_search.domain.usecase.SearchGameUsecase
import com.staygrateful.feature_search.external.extension.toGameSearchModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameSearchViewModel @Inject constructor(
    private val repository: SearchGameUsecase,
) : ViewModel() {

    private val _result = MutableStateFlow<Resource<SearchGameResponse?>>(Resource.None())
    val result: StateFlow<Resource<SearchGameResponse?>> get() = _result

    private val _items = MutableStateFlow<List<GameSearchModel>>(emptyList())
    val items: StateFlow<List<GameSearchModel>> get() = _items

    fun searchGames(query: String) {
        if (query.isEmpty()) return
        DelayUtil.collectLatest(500, query) {
            TimberLog.d("search query: $query")
            viewModelScope.launch {
                repository.searchGames(query).collectLatest {
                    _result.value = it
                    _items.value =
                        it.data?.results?.map { result ->
                            result.toGameSearchModel()
                        } ?: emptyList()
                }
            }
        }
    }
}
