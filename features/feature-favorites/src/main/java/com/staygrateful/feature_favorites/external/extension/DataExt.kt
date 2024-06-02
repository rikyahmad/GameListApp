package com.staygrateful.feature_favorites.external.extension

import com.staygrateful.core.model.GameModel
import com.staygrateful.feature_favorites.domain.model.GameFavoriteModel

fun GameModel.toGameFavoriteModel(): GameFavoriteModel {
    return GameFavoriteModel(
        id = id,
        gameId = gameId,
        name = name,
        released = released,
        description = description,
        developer = developer,
        genres = genres,
        backgroundImage = backgroundImage,
    )
}
