package com.staygrateful.core.extension

import com.staygrateful.core.model.GameModel
import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.model.GameResponse
import com.staygrateful.core.source.remote.model.SearchGameResponse

//const val Unknown = "Unknown"

fun GameResponse.Game.toGameEntity(): GameEntity {
    return GameEntity(
        0,
        this.id ?: 0,
        this.name,
        this.released?.convertDateFormat(),
        null,
        null,
        this.genres?.map { it.name ?: "" },
        this.background_image,
    )
}

fun SearchGameResponse.Game.toGameEntity(): GameEntity {
    return GameEntity(
        0,
        this.id ?: 0,
        this.name,
        this.released?.convertDateFormat(),
        null,
        null,
        this.genres?.map { it.name ?: "" },
        this.background_image,
    )
}

fun GameEntity.copy(description: String? = null, developer: String? = null): GameEntity {
    return GameEntity(
        this.id,
        this.gameId,
        this.name,
        this.released,
        description?.ifEmpty { null },
        developer?.ifEmpty { null },
        this.genres,
        this.backgroundImage
    )
}

fun GameEntity.merge(result: GameEntity): GameEntity {
    return this.copy(
        developer = if (result.developer?.isNotEmpty() == true) result.developer else this.developer,
        description = if (result.description?.isNotEmpty() == true) result.description else this.description,
    )
}

fun GameEntity.toFavoriteEntity(): FavoriteGameEntity {
    return FavoriteGameEntity(
        this.id,
        this.gameId,
        this.name,
        this.released,
        this.description?.ifEmpty { null },
        this.developer?.ifEmpty { null },
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
        this.description?.ifEmpty { null },
        this.developer?.ifEmpty { null },
        this.genres,
        this.backgroundImage,
    )
}

fun GameEntity.toGameModel(): GameModel {
    return GameModel(
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

fun FavoriteGameEntity.toGameModel(): GameModel {
    return GameModel(
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