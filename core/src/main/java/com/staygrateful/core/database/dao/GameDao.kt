package com.staygrateful.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.staygrateful.core.database.entity.GameEntity

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Query("SELECT * FROM games")
    suspend fun getGames(): List<GameEntity>
}
