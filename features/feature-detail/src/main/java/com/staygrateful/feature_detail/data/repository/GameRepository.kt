package com.staygrateful.feature_detail.data.repository

import androidx.paging.PagingData
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.feature_detail.data.source.local.GameLocalDataSource
import com.staygrateful.feature_detail.data.source.remote.GameRemoteDataSource
import kotlinx.coroutines.flow.Flow

interface GameRepository: GameLocalDataSource, GameRemoteDataSource
