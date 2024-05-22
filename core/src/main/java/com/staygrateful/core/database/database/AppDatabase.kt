package com.staygrateful.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.staygrateful.core.database.dao.GameDao
import com.staygrateful.core.database.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}
