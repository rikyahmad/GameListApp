package com.staygrateful.core.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.staygrateful.core.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games ORDER BY id")
    fun getItemsFlow(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games ORDER BY id")
    fun getPagingItems(): PagingSource<Int, GameEntity>

    @Query("SELECT * FROM games ORDER BY id LIMIT :pageSize OFFSET :offset")
    suspend fun getItems(pageSize: Int, offset: Int): List<GameEntity>

    @Upsert
    suspend fun upsertAll(games: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(games: List<GameEntity>)

    @Query("DELETE FROM games")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM games")
    suspend fun getCount(): Int
}
