package com.staygrateful.feature_search.di

import com.staygrateful.core.source.local.repository.DatabaseRepository
import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.feature_search.data.repository.GameRepository
import com.staygrateful.feature_search.data.repository.GameRepositoryImpl
import com.staygrateful.feature_search.data.source.local.GameLocalDataSource
import com.staygrateful.feature_search.data.source.local.GameLocalDataSourceImpl
import com.staygrateful.feature_search.data.source.remote.GameRemoteDataSource
import com.staygrateful.feature_search.data.source.remote.GameRemoteDataSourceImpl
import com.staygrateful.feature_search.domain.repository.SearchGamesRepository
import com.staygrateful.feature_search.domain.usecase.SearchGameUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchGameModule {

    @Provides
    fun provideGameLocalDataSource(repository: DatabaseRepository): GameLocalDataSource {
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
    fun provideGetGamesUseCase(gameRepository: GameRepository): SearchGameUsecase {
        return SearchGamesRepository(gameRepository)
    }
}