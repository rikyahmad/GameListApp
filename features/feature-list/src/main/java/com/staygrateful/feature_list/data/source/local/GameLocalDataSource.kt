package com.staygrateful.feature_list.data.source.local

import com.staygrateful.core.source.local.dao.GameDao
import com.staygrateful.core.source.local.entity.GameEntity
import javax.inject.Inject

class GameLocalDataSource @Inject constructor(
    private val gameDao: GameDao
) {
    suspend fun getGames(): List<GameEntity> {
        return gameDao.getGames()
    }

    suspend fun saveGames(games: List<GameEntity>) {
        gameDao.insertGames(games)
    }
}
