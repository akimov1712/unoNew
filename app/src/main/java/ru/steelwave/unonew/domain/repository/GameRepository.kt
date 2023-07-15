package ru.steelwave.unonew.domain.repository

import androidx.lifecycle.LiveData
import ru.steelwave.unonew.domain.entity.GameModel

interface GameRepository {

    fun getAllGames(): LiveData<List<GameModel>>
    suspend fun getGame(gameId: Int): GameModel
    suspend fun addGame(game: GameModel)

}