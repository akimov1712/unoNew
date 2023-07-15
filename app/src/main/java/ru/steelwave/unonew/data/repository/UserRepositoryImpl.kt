package ru.steelwave.unonew.data.repository

import android.app.Application
import androidx.lifecycle.Transformations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.steelwave.unonew.data.database.AppDatabase
import ru.steelwave.unonew.data.mapper.UserMapper
import ru.steelwave.unonew.domain.entity.UserModel
import ru.steelwave.unonew.domain.repository.UserRepository

class UserRepositoryImpl(application: Application):UserRepository {
    private val dao = AppDatabase.getInstance(application).userDao()
    private val mapper = UserMapper

    override suspend fun addUser(user: UserModel) {
        dao.insertUser(mapper.mapEntityToDbModel(user))
    }

    override suspend fun deleteUser(user: UserModel) {
        dao.deleteUser(mapper.mapEntityToDbModel(user))
    }

    override suspend fun getUser(userId: Int) = mapper.mapDbModelToEntity(dao.getUser(userId))

    override fun getAllUsers() = Transformations.map(dao.getAllUsers()){
        mapper.mapListDbModelToListEntity(it)
    }

//    init {
//        CoroutineScope(Dispatchers.IO).launch {
//            dao.deleteAllUsers()
//        }
//    }

}