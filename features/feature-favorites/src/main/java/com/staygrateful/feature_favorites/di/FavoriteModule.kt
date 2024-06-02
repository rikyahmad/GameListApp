package com.staygrateful.feature_favorites.di

import com.staygrateful.core.source.local.repository.DatabaseRepository
import com.staygrateful.core.source.remote.service.ApiService
import com.staygrateful.feature_favorites.data.repository.IFavoriteRepository
import com.staygrateful.feature_favorites.data.repository.FavoriteRepository
import com.staygrateful.feature_favorites.data.source.local.IFavoriteLocalDataSource
import com.staygrateful.feature_favorites.data.source.local.FavoriteLocalDataSource
import com.staygrateful.feature_favorites.data.source.remote.IFavoriteRemoteDataSource
import com.staygrateful.feature_favorites.data.source.remote.FavoriteRemoteDataSource
import com.staygrateful.feature_favorites.domain.repository.FavoriteGamesRepository
import com.staygrateful.feature_favorites.domain.usecase.FavoriteGameUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FavoriteModule {

    @Provides
    fun provideGameLocalDataSource(repository: DatabaseRepository): IFavoriteLocalDataSource {
        return FavoriteLocalDataSource(repository)
    }

    @Provides
    fun provideGameRemoteDataSource(apiService: ApiService): IFavoriteRemoteDataSource {
        return FavoriteRemoteDataSource(apiService)
    }

    @Provides
    fun provideGameRepository(
        localDataSource: IFavoriteLocalDataSource, remoteDataSource: IFavoriteRemoteDataSource
    ): IFavoriteRepository {
        return FavoriteRepository(localDataSource, remoteDataSource)
    }

    @Provides
    fun provideGetGamesUseCase(repository: IFavoriteRepository): FavoriteGameUsecase {
        return FavoriteGamesRepository(repository)
    }
}