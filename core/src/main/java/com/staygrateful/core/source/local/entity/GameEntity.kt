package com.staygrateful.core.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.staygrateful.core.source.local.converter.Converters
import com.staygrateful.core.source.remote.model.GameResponse

@Entity(tableName = "games", indices = [Index(value = ["gameId"], unique = true)])
@TypeConverters(Converters::class)
data class GameEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "gameId")
    val gameId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "genres")
    val genres: List<String>,
    @ColumnInfo(name = "background_image")
    val backgroundImage: String,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameEntity

        return gameId == other.gameId
    }

    override fun hashCode(): Int {
        return gameId.hashCode()
    }

    companion object {

        fun from(game: GameResponse.Game): GameEntity {
            return GameEntity(
                0,
                game.id ?: 0,
                game.name ?: "",
                game.released ?: "",
                game.genres?.map { it.name ?: "" }.orEmpty(),
                game.background_image  ?: ""
            )
        }
    }
}
