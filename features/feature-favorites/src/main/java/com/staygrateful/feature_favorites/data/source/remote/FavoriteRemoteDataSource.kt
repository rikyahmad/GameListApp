package com.staygrateful.feature_favorites.data.source.remote

import com.staygrateful.core.network.remote.service.ApiService
import javax.inject.Inject

class FavoriteRemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : IFavoriteRemoteDataSource { }
