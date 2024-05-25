package com.staygrateful.feature_detail.di

import com.staygrateful.core.network.local.repository.IDatabaseRepository
import com.staygrateful.core.network.remote.repository.INetworkRepository
import com.staygrateful.feature_detail.data.repository.DetailRepository
import com.staygrateful.feature_detail.data.repository.IDetailRepository
import com.staygrateful.feature_detail.data.source.local.DetailLocalDataSource
import com.staygrateful.feature_detail.data.source.local.IDetailLocalDataSource
import com.staygrateful.feature_detail.data.source.remote.DetailRemoteDataSource
import com.staygrateful.feature_detail.data.source.remote.IDetailRemoteDataSource
import com.staygrateful.feature_detail.domain.repository.DetailGamesRepository
import com.staygrateful.feature_detail.domain.usecase.DetailGameUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DetailModule {

    @Provides
    fun provideGameLocalDataSource(repository: IDatabaseRepository): IDetailLocalDataSource {
        return DetailLocalDataSource(repository)
    }

    @Provides
    fun provideGameRemoteDataSource(repository: INetworkRepository): IDetailRemoteDataSource {
        return DetailRemoteDataSource(repository)
    }

    @Provides
    fun provideGameRepository(
        localDataSource: IDetailLocalDataSource, remoteDataSource: IDetailRemoteDataSource
    ): IDetailRepository {
        return DetailRepository(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetGamesUseCase(repository: IDetailRepository): DetailGameUsecase {
        return DetailGamesRepository(repository)
    }
}