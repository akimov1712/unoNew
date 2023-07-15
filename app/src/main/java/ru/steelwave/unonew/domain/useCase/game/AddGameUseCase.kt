package ru.steelwave.unonew.domain.useCase.game

import androidx.lifecycle.LiveData
import ru.steelwave.unonew.domain.entity.GameModel
import ru.steelwave.unonew.domain.repository.GameRepository

class AddGameUseCase(private val repository: GameRepository) {
    suspend operator fun invoke(game: GameModel) {
        return repository.addGame(game)
    }
}