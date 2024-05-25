package com.staygrateful.core.extension

import com.staygrateful.core.network.local.entity.FavoriteGameEntity
import com.staygrateful.core.network.local.entity.GameEntity
import com.staygrateful.core.network.remote.model.GameResponse
import com.staygrateful.core.network.remote.model.SearchGameResponse

const val Unknown = "Unknown"

fun GameResponse.Game.toGameEntity(): GameEntity {
    return GameEntity(
        0,
        this.id ?: 0,
        this.name ?: Unknown,
        this.released?.convertDateFormat() ?: Unknown,
        Unknown,
        Unknown,
        this.genres?.map { it.name ?: Unknown } ?: listOf(Unknown),
        this.background_image ?: Unknown,
    )
}

fun SearchGameResponse.Game.toGameEntity(): GameEntity {
    return GameEntity(
        0,
        this.id ?: 0,
        this.name ?: Unknown,
        this.released?.convertDateFormat() ?: Unknown,
        Unknown,
        Unknown,
        this.genres?.map { it.name ?: Unknown } ?: listOf(Unknown),
        this.background_image ?: Unknown,
    )
}

fun GameEntity.copy(description: String = Unknown, developer: String = Unknown): GameEntity {
    return GameEntity(
        this.id,
        this.gameId,
        this.name,
        this.released,
        description.ifEmpty { Unknown },
        developer.ifEmpty { Unknown },
        this.genres,
        this.backgroundImage
    )
}

fun GameEntity.merge(result: GameEntity): GameEntity {
    return this.copy(
        developer = if (result.developer.isNotEmpty() && result.developer != Unknown) result.developer else this.developer,
        description = if (result.description.isNotEmpty() && result.description != Unknown) result.description else this.description,
    )
}

fun GameEntity.toFavoriteEntity(): FavoriteGameEntity {
    return FavoriteGameEntity(
        this.id,
        this.gameId,
        this.name,
        this.released,
        this.description.ifEmpty { Unknown },
        this.developer.ifEmpty { Unknown },
        this.genres,
        this.backgroundImage
    )
}

fun FavoriteGameEntity.toGameEntity(): GameEntity {
    return GameEntity(
        this.id,
        this.gameId,
        this.name,
        this.released,
        this.description.ifEmpty { Unknown },
        this.developer.ifEmpty { Unknown },
        this.genres,
        this.backgroundImage,
    )
}