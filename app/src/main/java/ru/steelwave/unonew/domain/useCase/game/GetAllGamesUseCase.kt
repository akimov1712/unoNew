package ru.steelwave.unonew.domain.useCase.game

import ru.steelwave.unonew.domain.repository.GameRepository

class GetAllGamesUseCase(private val repository: GameRepository) {
    operator fun invoke() = repository.getAllGames()
}