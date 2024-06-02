package com.staygrateful.feature_detail.external.extension

import com.staygrateful.core.model.GameModel
import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.feature_detail.domain.model.GameDetailModel

fun GameDetailModel.toFavoriteEntity(): FavoriteGameEntity {
    return FavoriteGameEntity(
        id = id,
        gameId = gameId,
        name = name,
        released = released,
        description = description,
        developer = developer,
        genres = genres.orEmpty(),
        backgroundImage = backgroundImage,
    )
}

fun GameEntity.toGameDetailModel(): GameDetailModel {
    return GameDetailModel(
        this.id,
        this.gameId,
        this.name,
        this.released,
        this.description?.ifEmpty { null },
        this.developer?.ifEmpty { null },
        this.genres,
        this.backgroundImage,
    )
}

fun GameModel.toGameDetailModel(): GameDetailModel {
    return GameDetailModel(
        this.id,
        this.gameId,
        this.name,
        this.released,
        this.description?.ifEmpty { null },
        this.developer?.ifEmpty { null },
        this.genres,
        this.backgroundImage,
    )
}