package com.staygrateful.core.source.remote.service

import com.google.gson.Gson
import com.staygrateful.core.BuildConfig
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.core.source.remote.model.GameResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject
import javax.inject.Named

open class ApiService @Inject constructor(
    private val httpClient: HttpClient,
    @Named("baseUrl")
    private val baseUrl: String = "https://api.rawg.io/api/",
): IApiService {

    override suspend fun getGameList(page: Int, pageSize: Int): GameResponse? {
        try {
            val response = httpClient.get("${baseUrl}games") {
                parameter("key", BuildConfig.API_KEY)
                parameter("page", page)
                parameter("page_size", pageSize)
            }.bodyAsText(Charsets.UTF_8)
            return Gson().fromJson(response, GameResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override suspend fun getDetailGame(gameId: Int): DetailGameResponse? {
        try {
            val url = "${baseUrl}games/$gameId"
            println("Detail game response : get -> $gameId, $url, ${BuildConfig.API_KEY}")
            val response = httpClient.get(url) {
                parameter("key", BuildConfig.API_KEY)
            }.bodyAsText(Charsets.UTF_8)
            println("Detail game response : $gameId | $response")
            return Gson().fromJson(response, DetailGameResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
