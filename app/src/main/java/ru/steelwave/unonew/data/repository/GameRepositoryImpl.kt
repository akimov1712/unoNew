package ru.steelwave.unonew.data.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.steelwave.unonew.data.database.AppDatabase
import ru.steelwave.unonew.data.mapper.GameMapper
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.repository.GameRepository

class GameRepositoryImpl(application: Application): GameRepository {
    private val dao = AppDatabase.getInstance(application).gameDao()
    private val mapper = GameMapper

    override fun getAllGames() = Transformations.map(dao.getAllGame()){
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getGame(gameId: Int) = mapper.mapDbModelToEntity(dao.getGame(gameId))

    override suspend fun addGame(game: GameModel) {
        dao.insertGame(mapper.mapEntityToDbModel(game))
    }
}