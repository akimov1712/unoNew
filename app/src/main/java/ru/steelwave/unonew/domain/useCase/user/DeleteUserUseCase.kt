package ru.steelwave.unonew.domain.useCase.user

import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.domain.repository.UserRepository

class DeleteUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserModel) {
        return repository.deleteUser(user)
    }
}