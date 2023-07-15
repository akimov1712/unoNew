package ru.steelwave.unonew.domain.repository

import androidx.lifecycle.LiveData
import ru.steelwave.unonew.domain.entity.UserModel

interface UserRepository {

    suspend fun addUser(user: UserModel)
    suspend fun deleteUser(user: UserModel)
    suspend fun getUser(userId: Int): UserModel
    fun getAllUsers(): LiveData<List<UserModel>>

}