package com.staygrateful.feature_favorites.di

import com.staygrateful.core.source.local.repository.DatabaseRepository
import com.staygrateful.core.source.remote.service.ApiService
import com.staygrateful.feature_favorites.data.repository.GameRepository
import com.staygrateful.feature_favorites.data.repository.GameRepositoryImpl
import com.staygrateful.feature_favorites.data.source.local.GameLocalDataSource
import com.staygrateful.feature_favorites.data.source.local.GameLocalDataSourceImpl
import com.staygrateful.feature_favorites.data.source.remote.GameRemoteDataSource
import com.staygrateful.feature_favorites.data.source.remote.GameRemoteDataSourceImpl
import com.staygrateful.feature_favorites.domain.repository.FavoriteGamesRepository
import com.staygrateful.feature_favorites.domain.usecase.FavoriteGameUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FavoriteGameModule {

    @Provides
    fun provideGameLocalDataSource(repository: DatabaseRepository): GameLocalDataSource {
        return GameLocalDataSourceImpl(repository)
    }

    @Provides
    fun provideGameRemoteDataSource(apiService: ApiService): GameRemoteDataSource {
        return GameRemoteDataSourceImpl(apiService)
    }

    @Provides
    fun provideGameRepository(
        localDataSource: GameLocalDataSource, remoteDataSource: GameRemoteDataSource
    ): GameRepository {
        return GameRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetGamesUseCase(gameRepository: GameRepository): FavoriteGameUsecase {
        return FavoriteGamesRepository(gameRepository)
    }
}