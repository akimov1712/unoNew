package ru.steelwave.unonew.domain.useCase.round

import ru.steelwave.unonew.domain.repository.RoundRepository

class GetRoundListUseCase (private val repository: RoundRepository){

    operator fun invoke(gameId: Int) = repository.getRoundListUseCase(gameId)

}