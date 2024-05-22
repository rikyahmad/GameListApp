package com.staygrateful.core.source

import com.staygrateful.core.BuildConfig
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.repository.INetworkRepository
import com.staygrateful.core.source.remote.repository.NetworkRepository
import com.staygrateful.core.source.remote.service.ApiService
import com.staygrateful.core.utils.Resource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ApiServiceTest {

    private lateinit var repository: INetworkRepository

    @Before
    fun setUp() {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                gson {
                    setLenient()
                    serializeNulls()
                    disableHtmlEscaping()
                }
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }

        repository = NetworkRepository(ApiService(client, apiKey = BuildConfig.API_KEY))
    }

    @Test
    fun getGamesSuccess() = runBlocking {
        val response = repository.getGames()
        val result = mutableListOf<Resource<GameResponse>>()

        response.collect { value ->
            result.add(value)
        }

        assertEquals(result.size, 2)
        assertEquals(true, result[0] is Resource.Loading)
        assertEquals(true, result[1] is Resource.Success)
    }
}