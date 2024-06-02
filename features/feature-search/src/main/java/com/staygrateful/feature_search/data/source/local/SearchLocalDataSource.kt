package com.staygrateful.feature_search.data.source.local

import com.staygrateful.core.source.local.repository.IDatabaseRepository
import javax.inject.Inject

class SearchLocalDataSource @Inject constructor(
    private val repository: IDatabaseRepository
) : ISearchLocalDataSource { }
