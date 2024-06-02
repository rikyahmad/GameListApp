package com.staygrateful.feature_detail.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GameDetailModel(
    val id: Int,
    val gameId: Int,
    val name: String?,
    val released: String?,
    val description: String?,
    val developer: String?,
    val genres: List<String>?,
    val backgroundImage: String?,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameDetailModel

        if (gameId != other.gameId) return false
        if (name != other.name) return false
        if (released != other.released) return false
        if (description != other.description) return false
        if (developer != other.developer) return false
        if (genres != other.genres) return false
        if (backgroundImage != other.backgroundImage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gameId
        result = 31 * result + name.hashCode()
        result = 31 * result + released.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + developer.hashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + backgroundImage.hashCode()
        return result
    }
}