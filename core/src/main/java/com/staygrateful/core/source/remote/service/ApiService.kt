package com.staygrateful.core.source.remote.service

import com.google.gson.Gson
import com.staygrateful.core.BuildConfig
import com.staygrateful.core.source.remote.model.DetailGameResponse
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.model.SearchGameResponse
import com.staygrateful.core.utils.TimberLog
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject
import javax.inject.Named

open class ApiService @Inject constructor(
    private val httpClient: HttpClient,
    @Named("baseUrl")
    private val baseUrl: String = BASE_URL,
): IApiService {

    override suspend fun getGameList(page: Int, pageSize: Int): GameResponse? {
        try {
            TimberLog.d("API get list game query : $page, $pageSize")
            val response = httpClient.get("${baseUrl}games") {
                parameter("key", BuildConfig.API_KEY)
                parameter("page", page)
                parameter("page_size", pageSize)
            }.bodyAsText(Charsets.UTF_8)
            TimberLog.d("API get list game response : $response")
            return Gson().fromJson(response, GameResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override suspend fun getDetailGame(gameId: Int): DetailGameResponse? {
        try {
            TimberLog.d("API get detail game query : $gameId")
            val response = httpClient.get("${baseUrl}games/$gameId") {
                parameter("key", BuildConfig.API_KEY)
            }.bodyAsText(Charsets.UTF_8)
            TimberLog.d("API get detail game response : $response")
            return Gson().fromJson(response, DetailGameResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override suspend fun searchGames(query: String): SearchGameResponse? {
        try {
            TimberLog.d("API search games query : $query")
            val response = httpClient.get("${baseUrl}games") {
                parameter("search", query)
                parameter("key", BuildConfig.API_KEY)
            }.bodyAsText(Charsets.UTF_8)
            TimberLog.d("API search games response : $response")
            return Gson().fromJson(response, SearchGameResponse::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    companion object {

        const val BASE_URL = "https://api.rawg.io/api/"
    }
}
