package com.staygrateful.core.extension

import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.model.GameResponse

const val Unknown = "unknown"

fun GameResponse.Game.toGameEntity(): GameEntity {
    return GameEntity(
        0,
        this.id ?: 0,
        this.name ?: Unknown,
        this.released?.convertDateFormat() ?: Unknown,
        Unknown,
        Unknown,
        this.genres?.map { it.name ?: Unknown }.orEmpty(),
        this.background_image  ?: Unknown,
    )
}

fun GameEntity.merge(result: GameEntity): GameEntity {
    return this.copy(
        developer = if (result.developer.isNotEmpty() && result.developer != Unknown) result.developer else this.developer,
        description = if (result.description.isNotEmpty() && result.description != Unknown) result.description else this.description,
    )
}