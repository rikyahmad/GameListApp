package com.staygrateful.gamelistapp.extension

import com.staygrateful.feature_detail.domain.model.GameDetailModel
import com.staygrateful.feature_favorites.domain.model.GameFavoriteModel
import com.staygrateful.feature_list.domain.model.GameListModel
import com.staygrateful.feature_search.domain.model.GameSearchModel

fun GameListModel.toGameDetailModel(): GameDetailModel {
    return GameDetailModel(
        id = id,
        gameId = gameId,
        name = name,
        released = released,
        description = description,
        developer = developer,
        genres = genres?.ifEmpty { null },
        backgroundImage = backgroundImage,
    )
}

fun GameFavoriteModel.toGameDetailModel(): GameDetailModel {
    return GameDetailModel(
        id = id,
        gameId = gameId,
        name = name,
        released = released,
        description = description,
        developer = developer,
        genres = genres?.ifEmpty { null },
        backgroundImage = backgroundImage,
    )
}

fun GameSearchModel.toGameDetailModel(): GameDetailModel {
    return GameDetailModel(
        id = id,
        gameId = gameId,
        name = name,
        released = released,
        description = null,
        developer = null,
        genres = genres?.ifEmpty { null },
        backgroundImage = backgroundImage,
    )
}