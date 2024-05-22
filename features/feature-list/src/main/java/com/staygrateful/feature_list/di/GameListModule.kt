package com.staygrateful.feature_list.di

import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.feature_list.data.repository.GameRepository
import com.staygrateful.feature_list.domain.usecase.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GameListModule {

    @Provides
    fun provideGameRepository(repository: INetworkRepository): GameRepository {
        return GameRepository(repository)
    }

    @Provides
    fun provideGetGamesUseCase(gameRepository: GameRepository): GetGamesUseCase {
        return GetGamesUseCase(gameRepository)
    }
}