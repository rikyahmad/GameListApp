package com.staygrateful.feature_search.data.source.local

import androidx.paging.PagingSource
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.local.repository.IDatabaseRepository
import com.staygrateful.feature_search.data.source.local.GameLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameLocalDataSourceImpl @Inject constructor(
    private val repository: IDatabaseRepository
) : GameLocalDataSource {

}
