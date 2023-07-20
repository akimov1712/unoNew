package ru.steelwave.unonew.domain.useCase.round

import ru.steelwave.unonew.domain.entity.RoundModel
import ru.steelwave.unonew.domain.repository.RoundRepository

class AddRoundUseCase(private val repository: RoundRepository) {

    suspend operator fun invoke(round: RoundModel){
        repository.addRoundUseCase(round)
    }

}