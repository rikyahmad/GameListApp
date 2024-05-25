package com.staygrateful.feature_search.data.source.local

import com.staygrateful.core.network.local.repository.IDatabaseRepository
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    private val repository: IDatabaseRepository
) : ISearchLocalDataSource { }
