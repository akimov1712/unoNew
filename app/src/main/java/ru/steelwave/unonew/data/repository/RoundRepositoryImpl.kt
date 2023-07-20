package ru.steelwave.unonew.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.steelwave.unonew.data.database.AppDatabase
import ru.steelwave.unonew.data.mapper.RoundMapper
import ru.steelwave.unonew.domain.entity.RoundModel
import ru.steelwave.unonew.domain.repository.RoundRepository

class RoundRepositoryImpl(application: Application): RoundRepository {
    private val dao = AppDatabase.getInstance(application).roundDao()
    private val mapper = RoundMapper

    override fun getRoundListUseCase(gameId: Int): LiveData<List<RoundModel>> {
        return Transformations.map(dao.getRoundList(gameId)){
            mapper.mapDbModelListToEntityList(it)
        }
    }

    override suspend fun addRoundUseCase(round: RoundModel) {
        dao.insertRound(mapper.mapEntityToDbModel(round))
    }

}