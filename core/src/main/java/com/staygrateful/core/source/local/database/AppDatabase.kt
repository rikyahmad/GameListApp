package com.staygrateful.core.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.staygrateful.core.source.local.converter.Converters
import com.staygrateful.core.source.local.dao.GameDao
import com.staygrateful.core.source.local.entity.FavoriteGameEntity
import com.staygrateful.core.source.local.entity.GameEntity

@Database(
    entities = [GameEntity::class, FavoriteGameEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: GameDao
}
