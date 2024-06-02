package com.staygrateful.feature_search.di

import com.staygrateful.core.source.local.repository.DatabaseRepository
import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.feature_search.data.repository.ISearchRepository
import com.staygrateful.feature_search.data.repository.SearchRepository
import com.staygrateful.feature_search.data.source.local.ISearchLocalDataSource
import com.staygrateful.feature_search.data.source.local.SearchLocalDataSource
import com.staygrateful.feature_search.data.source.remote.ISearchRemoteDataSource
import com.staygrateful.feature_search.data.source.remote.SearchRemoteDataSource
import com.staygrateful.feature_search.domain.repository.SearchGamesRepository
import com.staygrateful.feature_search.domain.usecase.SearchGameUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @Provides
    fun provideGameLocalDataSource(repository: DatabaseRepository): ISearchLocalDataSource {
        return SearchLocalDataSource(repository)
    }

    @Provides
    fun provideGameRemoteDataSource(repository: INetworkRepository): ISearchRemoteDataSource {
        return SearchRemoteDataSource(repository)
    }

    @Provides
    fun provideGameRepository(
        localDataSource: ISearchLocalDataSource,
        remoteDataSource: ISearchRemoteDataSource
    ): ISearchRepository {
        return SearchRepository(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetGamesUseCase(repository: ISearchRepository): SearchGameUsecase {
        return SearchGamesRepository(repository)
    }
}