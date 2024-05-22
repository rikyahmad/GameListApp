package com.staygrateful.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val released: String,
    val background_image: String
)
