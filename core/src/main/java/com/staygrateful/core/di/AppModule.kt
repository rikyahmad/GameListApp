package com.staygrateful.core.di

import android.content.Context
import androidx.room.Room
import com.staygrateful.core.source.local.dao.GameDao
import com.staygrateful.core.source.local.database.AppDatabase
import com.staygrateful.core.source.local.repository.DatabaseRepository
import com.staygrateful.core.source.local.repository.IDatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "game_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameDao(appDatabase: AppDatabase): GameDao {
        return appDatabase.dao
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        appDatabase: AppDatabase
    ): IDatabaseRepository {
        return DatabaseRepository(appDatabase)
    }
}