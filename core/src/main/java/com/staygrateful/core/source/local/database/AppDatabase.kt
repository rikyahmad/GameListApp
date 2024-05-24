package com.staygrateful.core.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.staygrateful.core.source.local.converter.Converters
import com.staygrateful.core.source.local.dao.GameDao
import com.staygrateful.core.source.local.entity.GameEntity
import com.staygrateful.core.source.local.entity.FavoriteEntity

@Database(entities = [GameEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: GameDao
}
