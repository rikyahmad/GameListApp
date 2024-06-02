package com.staygrateful.core.source.remote.mapper

import com.staygrateful.core.utils.TimberLog

abstract class ApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        try {
            val response = apiCall()
            TimberLog.d("API response : $response")
            if (response != null) {
                return Resource.Success(data = response)
            }
            throw Exception("An unexpected error occurred with null response")
        } catch (e: Exception) {
            TimberLog.d("API response with error: ${e.localizedMessage}")
            return Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}