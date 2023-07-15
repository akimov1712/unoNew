package ru.steelwave.unonew.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.steelwave.unonew.data.database.model.GameDbModel

@Dao
interface GameDao {

    @Query("SELECT * FROM game ORDER BY gameId DESC")
    fun getAllGame():LiveData<List<GameDbModel>>

    @Query("DELETE FROM game")
    fun deleteAllGame()

    @Query("SELECT * FROM game WHERE gameId == :gameId LIMIT 1")
    fun getGame(gameId: Int): LiveData<GameDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameDbModel)

}