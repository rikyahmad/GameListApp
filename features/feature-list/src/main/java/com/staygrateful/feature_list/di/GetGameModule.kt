package com.staygrateful.feature_list.di

import com.staygrateful.core.source.local.database.AppDatabase
import com.staygrateful.core.source.remote.service.ApiService
import com.staygrateful.feature_list.data.repository.GameRepository
import com.staygrateful.feature_list.data.repository.GameRepositoryImpl
import com.staygrateful.feature_list.data.source.local.GameLocalDataSource
import com.staygrateful.feature_list.data.source.local.GameLocalDataSourceImpl
import com.staygrateful.feature_list.data.source.remote.GameRemoteDataSource
import com.staygrateful.feature_list.data.source.remote.GameRemoteDataSourceImpl
import com.staygrateful.feature_list.domain.repository.GetGamesRepository
import com.staygrateful.feature_list.domain.usecase.GetGameUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GetGameModule {

    @Provides
    fun provideGameLocalDataSource(appDatabase: AppDatabase): GameLocalDataSource {
        return GameLocalDataSourceImpl(appDatabase)
    }

    @Provides
    fun provideGameRemoteDataSource(apiService: ApiService): GameRemoteDataSource {
        return GameRemoteDataSourceImpl(apiService)
    }

    @Provides
    fun provideGameRepository(
        localDataSource: GameLocalDataSource,
        remoteDataSource: GameRemoteDataSource
    ): GameRepository {
        return GameRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetGamesUseCase(gameRepository: GameRepository): GetGameUsecase {
        return GetGamesRepository(gameRepository)
    }
}