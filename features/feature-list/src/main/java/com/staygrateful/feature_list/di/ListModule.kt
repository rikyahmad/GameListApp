package com.staygrateful.feature_list.di

import com.staygrateful.core.network.local.repository.DatabaseRepository
import com.staygrateful.core.network.remote.repository.INetworkRepository
import com.staygrateful.feature_list.data.repository.IListRepository
import com.staygrateful.feature_list.data.repository.ListRepository
import com.staygrateful.feature_list.data.source.local.IListLocalDataSource
import com.staygrateful.feature_list.data.source.local.ListLocalDataSource
import com.staygrateful.feature_list.data.source.remote.IListRemoteDataSource
import com.staygrateful.feature_list.data.source.remote.ListRemoteDataSource
import com.staygrateful.feature_list.domain.repository.ListGamesRepository
import com.staygrateful.feature_list.domain.usecase.ListGameUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ListModule {

    @Provides
    fun provideGameLocalDataSource(repository: DatabaseRepository): IListLocalDataSource {
        return ListLocalDataSource(repository)
    }

    @Provides
    fun provideGameRemoteDataSource(repository: INetworkRepository): IListRemoteDataSource {
        return ListRemoteDataSource(repository)
    }

    @Provides
    fun provideGameRepository(
        localDataSource: IListLocalDataSource,
        remoteDataSource: IListRemoteDataSource
    ): IListRepository {
        return ListRepository(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetGamesUseCase(repository: IListRepository): ListGameUsecase {
        return ListGamesRepository(repository)
    }
}