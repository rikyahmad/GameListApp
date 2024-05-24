package com.staygrateful.feature_favorites.data.source.remote

import com.staygrateful.core.source.remote.service.ApiService
import javax.inject.Inject

class GameRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : GameRemoteDataSource {

}
