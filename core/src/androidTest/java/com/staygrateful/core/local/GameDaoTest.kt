package com.staygrateful.core.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.staygrateful.core.network.local.dao.GameDao
import com.staygrateful.core.network.local.database.AppDatabase
import com.staygrateful.core.network.local.entity.FavoriteGameEntity
import com.staygrateful.core.network.local.entity.GameEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class GameDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var gameDao: GameDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        gameDao = database.dao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertFavoriteGameAndGetFavoriteGames() = runTest {
        val favoriteGame = FavoriteGameEntity(
            id = 1,
            gameId = 1,
            name = "Game 1",
            released = "2022-01-01",
            description = "Description",
            developer = "Developer",
            genres = listOf("Action"),
            backgroundImage = "image_url"
        )
        gameDao.insertFavorite(favoriteGame)

        val favoriteGames = gameDao.getFavoriteGames().first()
        assertEquals(1, favoriteGames.size)
        assertEquals(favoriteGame, favoriteGames[0])
    }

    @Test
    fun deleteFavoriteGameByGameId() = runTest {
        val favoriteGame = FavoriteGameEntity(
            id = 1,
            gameId = 1,
            name = "Game 1",
            released = "2022-01-01",
            description = "Description",
            developer = "Developer",
            genres = listOf("Action"),
            backgroundImage = "image_url"
        )
        gameDao.insertFavorite(favoriteGame)
        gameDao.deleteFavoriteByGameId(1)

        val favoriteGames = gameDao.getFavoriteGames().first()
        assertTrue(favoriteGames.isEmpty())
    }

    @Test
    fun isGameFavorite() = runTest {
        val favoriteGame = FavoriteGameEntity(
            id = 1,
            gameId = 1,
            name = "Game 1",
            released = "2022-01-01",
            description = "Description",
            developer = "Developer",
            genres = listOf("Action"),
            backgroundImage = "image_url"
        )
        gameDao.insertFavorite(favoriteGame)

        val isFavorite = gameDao.isGameFavorite(1)
        assertTrue(isFavorite)
    }

    @Test
    fun insertAndGetGame() = runTest {
        val game = GameEntity(
            id = 1,
            gameId = 1,
            name = "Game 1",
            released = "2022-01-01",
            description = "Description",
            developer = "Developer",
            genres = listOf("Action"),
            backgroundImage = "image_url"
        )
        gameDao.insert(game)

        val retrievedGame = gameDao.getItem(1)
        assertNotNull(retrievedGame)
        assertEquals(game, retrievedGame)
    }

    @Test
    fun updateGameByGameId() = runTest {
        val game = GameEntity(
            id = 1,
            gameId = 1,
            name = "Game 1",
            released = "2022-01-01",
            description = "Description",
            developer = "Developer",
            genres = listOf("Action"),
            backgroundImage = "image_url"
        )
        gameDao.insert(game)

        gameDao.updateByGameId(1, "New Developer", "New Description")

        val updatedGame = gameDao.getItem(1)
        assertNotNull(updatedGame)
        assertEquals("New Developer", updatedGame?.developer)
        assertEquals("New Description", updatedGame?.description)
    }

    @Test
    fun clearAllGames() = runTest {
        val game = GameEntity(
            id = 1,
            gameId = 1,
            name = "Game 1",
            released = "2022-01-01",
            description = "Description",
            developer = "Developer",
            genres = listOf("Action"),
            backgroundImage = "image_url"
        )
        gameDao.insert(game)
        gameDao.clearAll()

        val games = gameDao.getItemsFlow().first()
        assertTrue(games.isEmpty())
    }
}
