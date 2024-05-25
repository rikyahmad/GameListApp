package com.staygrateful.core.di

import android.content.Context
import com.staygrateful.core.BuildConfig
import com.staygrateful.core.helper.INetworkMonitor
import com.staygrateful.core.helper.NetworkMonitor
import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.core.source.remote.repository.NetworkRepository
import com.staygrateful.core.source.remote.service.ApiService
import com.staygrateful.core.source.remote.service.IApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                gson {
                    setLenient()
                    serializeNulls()

                }
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.INFO
                }
            }
        }
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "https://api.rawg.io/api/"
    }

    @Provides
    @Singleton
    fun provideApiService(
        httpClient: HttpClient,
        @Named("baseUrl")
        baseUrl: String,
    ): IApiService {
        return ApiService(httpClient, baseUrl)
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(
        apiService: IApiService
    ): INetworkRepository {
        return NetworkRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(
        @ApplicationContext context: Context
    ): INetworkMonitor {
        return NetworkMonitor(context)
    }
}

