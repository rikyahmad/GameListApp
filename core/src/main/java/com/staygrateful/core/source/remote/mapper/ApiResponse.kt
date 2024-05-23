package com.staygrateful.core.source.remote.mapper

abstract class ApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        try {
            val response = apiCall()
            if (response != null) {
                return Resource.Success(data = response)
            }
            throw Exception("Invalid response")
        } catch (e: Exception) {
            return Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}