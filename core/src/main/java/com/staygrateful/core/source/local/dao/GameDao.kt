package com.staygrateful.core.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.staygrateful.core.source.local.entity.FavoriteEntity
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("SELECT games.* FROM games INNER JOIN favorite ON games.gameId = favorite.gameId")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Query("DELETE FROM favorite WHERE gameId = :gameId")
    suspend fun deleteFavoriteByGameId(gameId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite WHERE gameId = :gameId)")
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

    @Upsert
    suspend fun upsertAll(games: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: GameEntity)

    @Query("DELETE FROM games")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM games")
    suspend fun getCount(): Int
}
