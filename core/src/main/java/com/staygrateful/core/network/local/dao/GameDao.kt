package com.staygrateful.core.network.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.staygrateful.core.network.local.entity.FavoriteGameEntity
import com.staygrateful.core.network.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM favorite_games ORDER BY id")
    fun getFavoriteGames(): Flow<List<FavoriteGameEntity>>

    @Query("SELECT favorite_games.* FROM favorite_games INNER JOIN games ON games.gameId = favorite_games.gameId")
    fun getItemsWithFavoriteGames(): Flow<List<FavoriteGameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = FavoriteGameEntity::class)
    suspend fun insertFavorite(favorite: FavoriteGameEntity)

    @Query("DELETE FROM favorite_games WHERE gameId IN (:gameIds)")
    suspend fun deleteFavoritesByGameIds(gameIds: List<Int>)

    @Query("DELETE FROM favorite_games WHERE gameId = :gameId")
    suspend fun deleteFavoriteByGameId(gameId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_games WHERE gameId = :gameId)")
    suspend fun isGameFavorite(gameId: Int): Boolean

    @Query("UPDATE games SET description = :description, developer = :developer WHERE gameId = :gameId")
    suspend fun updateByGameId(gameId: Int, developer: String, description: String)

    @Query("SELECT * FROM games ORDER BY id")
    fun getItemsFlow(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games ORDER BY id")
    fun getPagingItems(): PagingSource<Int, GameEntity>

    @Query("SELECT * FROM games ORDER BY id LIMIT :pageSize OFFSET :offset")
    suspend fun getItems(pageSize: Int, offset: Int): List<GameEntity>

    @Query("SELECT * FROM games WHERE gameId = :gameId")
    fun getItem(gameId: Int): GameEntity?

    @Upsert(entity = GameEntity::class)
    suspend fun upsertAll(games: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = GameEntity::class)
    suspend fun insertAll(games: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = GameEntity::class)
    suspend fun insert(game: GameEntity)

    @Query("DELETE FROM games")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM games")
    suspend fun getCount(): Int
}
