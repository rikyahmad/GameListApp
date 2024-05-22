package com.staygrateful.feature_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staygrateful.core.source.remote.repository.NetworkRepository
import com.staygrateful.core.utils.Resource
import com.staygrateful.feature_list.domain.usecase.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val repository: NetworkRepository,
) : ViewModel() {

    //private val _games = MutableLiveData<List<GameDomainModel>>()
    //val games: LiveData<List<GameDomainModel>> get() = _games

    fun fetchGames() {
        viewModelScope.launch(Dispatchers.IO) {
            println("Try get games")
            getGamesUseCase.getGames().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        println("Resource Success, data: ${resource.data}")
                    }
                    is Resource.Error -> {
                        println("Resource Error, message: ${resource.message}, data: ${resource.data}")
                    }
                    is Resource.Loading -> {
                        println("Resource Loading, data: ${resource.data}")
                    }
                }
            }
        }
    }
}
