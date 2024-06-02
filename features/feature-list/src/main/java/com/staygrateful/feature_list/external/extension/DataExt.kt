package com.staygrateful.feature_list.external.extension

import com.staygrateful.core.model.GameModel
import com.staygrateful.feature_list.domain.model.GameListModel

fun GameModel.toGameListModel(): GameListModel {
    return GameListModel(
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