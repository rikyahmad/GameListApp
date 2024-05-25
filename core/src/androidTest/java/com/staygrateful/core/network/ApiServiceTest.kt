package com.staygrateful.core.network

import com.staygrateful.core.network.remote.service.ApiService
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
import org.junit.Test

class ApiServiceTest {

    private val mockEngine = MockEngine { request ->
        when (request.url.encodedPath) {
            "/games" -> {
                respond(
                    content = """{"results": [{"id": 1, "name": "Game 1"}]}""",
                    status = HttpStatusCode.OK,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )
                )
            }

            "/games/1" -> {
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
        val response = apiService.getGameList(1, 10)
        assertNotNull(response)
        assertEquals(1, response?.results?.size)
        assertEquals("Game 1", response?.results?.get(0)?.name)
    }

    @Test
    fun testGetDetailGame() = runBlocking {
        val response = apiService.getDetailGame(1)
        assertNotNull(response)
        assertEquals(1, response?.id)
        assertEquals("Game 1", response?.name)
    }

    @Test
    fun testSearchGames() = runBlocking {
        val response = apiService.searchGames("Game 1")
        assertNotNull(response)
        assertEquals(1, response?.results?.size)
        assertEquals("Game 1", response?.results?.get(0)?.name)
    }
}
