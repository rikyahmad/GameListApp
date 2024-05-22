package com.staygrateful.core.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.staygrateful.core.source.local.dao.GameDao
import com.staygrateful.core.source.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
