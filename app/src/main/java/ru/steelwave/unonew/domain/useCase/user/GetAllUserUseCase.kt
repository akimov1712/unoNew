package ru.steelwave.unonew.domain.useCase.user

import ru.steelwave.unonew.domain.repository.UserRepository

class GetAllUserUseCase(private val repository: UserRepository) {
    operator fun invoke() = repository.getAllUsers()
}