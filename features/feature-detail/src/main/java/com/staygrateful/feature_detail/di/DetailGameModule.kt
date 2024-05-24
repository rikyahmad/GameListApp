package com.staygrateful.feature_detail.di

import com.staygrateful.core.source.local.database.AppDatabase
import com.staygrateful.core.source.local.repository.DatabaseRepository
import com.staygrateful.core.source.local.repository.IDatabaseRepository
import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.core.source.remote.service.ApiService
import com.staygrateful.feature_detail.data.repository.GameRepository
import com.staygrateful.feature_detail.data.repository.GameRepositoryImpl
import com.staygrateful.feature_detail.data.source.local.GameLocalDataSource
import com.staygrateful.feature_detail.data.source.local.GameLocalDataSourceImpl
import com.staygrateful.feature_detail.data.source.remote.GameRemoteDataSource
import com.staygrateful.feature_detail.data.source.remote.GameRemoteDataSourceImpl
import com.staygrateful.feature_detail.domain.repository.DetailGamesRepository
import com.staygrateful.feature_detail.domain.usecase.DetailGameUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DetailGameModule {

    @Provides
    fun provideGameLocalDataSource(repository: IDatabaseRepository): GameLocalDataSource {
        return GameLocalDataSourceImpl(repository)
    }

    @Provides
    fun provideGameRemoteDataSource(repository: INetworkRepository): GameRemoteDataSource {
        return GameRemoteDataSourceImpl(repository)
    }

    @Provides
    fun provideGameRepository(
        localDataSource: GameLocalDataSource,
        remoteDataSource: GameRemoteDataSource
    ): GameRepository {
        return GameRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetGamesUseCase(gameRepository: GameRepository): DetailGameUsecase {
        return DetailGamesRepository(gameRepository)
    }
}