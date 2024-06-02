package com.staygrateful.feature_search.external.extension

import com.staygrateful.core.extension.convertDateFormat
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.remote.model.SearchGameResponse
import com.staygrateful.feature_search.domain.model.GameSearchModel

fun GameEntity.toGameSearchModel(): GameSearchModel {
    return GameSearchModel(
        id = id,
        gameId = gameId,
        name = name,
        released = released,
        genres = genres,
        backgroundImage = backgroundImage,
    )
}

fun SearchGameResponse.Game.toGameSearchModel(): GameSearchModel {
    return GameSearchModel(
        0,
        this.id ?: 0,
        this.name,
        this.released?.convertDateFormat(),
        this.genres?.map { it.name ?: "" },
        this.background_image,
    )
}