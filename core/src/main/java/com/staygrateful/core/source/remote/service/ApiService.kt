package com.staygrateful.core.source.remote.service

import com.google.gson.Gson
import com.staygrateful.core.BuildConfig
import com.staygrateful.core.source.remote.model.GameResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

open class ApiService @Inject constructor(
    private val httpClient: HttpClient,
    private val baseUrl: String = "https://api.rawg.io/api/",
    private val apiKey: String = BuildConfig.API_KEY
): IApiService {

    override suspend fun getGames(): GameResponse? {
        try {
            val response = httpClient.get("${baseUrl}games") {
                parameter("key", apiKey)
            }.bodyAsText(Charsets.UTF_8)
            return Gson().fromJson(response, GameResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
