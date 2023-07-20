package ru.steelwave.unonew.domain.repository

import androidx.lifecycle.LiveData
import ru.steelwave.unonew.domain.entity.RoundModel

interface RoundRepository {

    fun getRoundListUseCase(gameId: Int): LiveData<List<RoundModel>>
    suspend fun addRoundUseCase(round: RoundModel)

}