package ru.steelwave.unonew.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.steelwave.unonew.data.database.model.UserDbModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<UserDbModel>>

    @Query("DELETE FROM user")
    fun deleteAllUsers()

    @Query("SELECT * FROM user WHERE userId=:userId LIMIT 1")
    suspend fun getUser(userId: Int): UserDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDbModel)

    @Delete
    suspend fun deleteUser(user: UserDbModel)

}