package com.staygrateful.core.source

import com.staygrateful.core.source.remote.service.ApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class ApiServiceTest {

    @Before
    fun setUp() {
        // Initialize Timber for logging
        Timber.plant(Timber.DebugTree())
    }

    private val mockEngine = MockEngine { request ->
        Timber.d("Request URL: ${request.url}")

        when (request.url.encodedPath) {
            "/api/games" -> {
                Timber.d("Mock request to /games")
                respond(
                    content = """{"results": [{"id": 1, "name": "Game 1"}]}""",
                    status = HttpStatusCode.OK,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )
                )
            }

            "/api/games/1" -> {
                Timber.d("Mock request to /games/1")
                respond(
                    content = """{"id": 1, "name": "Game 1"}""",
                    status = HttpStatusCode.OK,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )
                )
            }

            else -> {
                Timber.d("Mock request to an unknown endpoint: ${request.url.encodedPath}")
                respond(
                    content = """{"results": []}""",
                    status = HttpStatusCode.NotFound,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )
                )
            }
        }
    }

    private val client = HttpClient(mockEngine) {
        install(ContentNegotiation) {
            gson()
        }
    }

    private val apiService = ApiService(client, "https://api.rawg.io/api/")

    @Test
    fun testGetGameList() = runBlocking {
        Timber.d("Testing getGameList")
        val response = apiService.getGameList(1, 10)
        Timber.d("Response: $response")
        assertNotNull(response)
        assertEquals(1, response?.results?.size)
        assertEquals("Game 1", response?.results?.get(0)?.name)
    }

    @Test
    fun testGetDetailGame() = runBlocking {
        Timber.d("Testing getDetailGame")
        val response = apiService.getDetailGame(1)
        Timber.d("Response: $response")
        assertNotNull(response)
        assertEquals(1, response?.id)
        assertEquals("Game 1", response?.name)
    }

    @Test
    fun testSearchGames() = runBlocking {
        Timber.d("Testing searchGames")
        val response = apiService.searchGames("Game 1")
        Timber.d("Response: $response")
        assertNotNull(response)
        assertEquals(1, response?.results?.size)
        assertEquals("Game 1", response?.results?.get(0)?.name)
    }
}
