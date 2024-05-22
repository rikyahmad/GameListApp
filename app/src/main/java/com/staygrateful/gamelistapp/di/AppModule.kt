package com.staygrateful.gamelistapp.di

import android.content.Context
import androidx.room.Room
import com.staygrateful.core.database.dao.GameDao
import com.staygrateful.core.database.database.AppDatabase
import com.staygrateful.core.network.service.ApiService
import com.staygrateful.feature_list.data.repository.GameRepository
import com.staygrateful.feature_list.domain.repository.GameDomainRepository
import com.staygrateful.feature_list.domain.usecase.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameDao(appDatabase: AppDatabase): GameDao {
        return appDatabase.gameDao()
    }

    @Provides
    @Singleton
    fun provideGameRepository(gameDao: GameDao, apiService: ApiService): GameRepository {
        return GameRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideGetGamesUseCase(gameRepository: GameDomainRepository): GetGamesUseCase {
        return GetGamesUseCase(gameRepository)
    }
}
