package com.staygrateful.core.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.staygrateful.core.source.local.converter.Converters
import kotlinx.serialization.Serializable

@Entity(tableName = "games", indices = [Index(value = ["gameId"], unique = true)])
@Serializable
@TypeConverters(Converters::class)
open class GameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "gameId")
    val gameId: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "released")
    val released: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "developer")
    val developer: String?,
    @ColumnInfo(name = "genres")
    val genres: List<String>?,
    @ColumnInfo(name = "background_image")
    val backgroundImage: String?,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameEntity

        if (gameId != other.gameId) return false
        if (name != other.name) return false
        if (released != other.released) return false
        if (description != other.description) return false
        if (developer != other.developer) return false
        if (genres != other.genres) return false
        if (backgroundImage != other.backgroundImage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = gameId
        result = 31 * result + name.hashCode()
        result = 31 * result + released.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + developer.hashCode()
        result = 31 * result + genres.hashCode()
        result = 31 * result + backgroundImage.hashCode()
        return result
    }
}
