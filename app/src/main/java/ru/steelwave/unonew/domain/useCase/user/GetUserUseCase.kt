package ru.steelwave.unonew.domain.useCase.user

import androidx.lifecycle.LiveData
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.domain.repository.UserRepository

class GetUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(userId: Int) = repository.getUser(userId)
}