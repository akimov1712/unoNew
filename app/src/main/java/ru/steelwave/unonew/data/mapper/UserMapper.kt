package ru.steelwave.unonew.data.mapper

import ru.steelwave.unonew.data.database.model.UserDbModel
import ru.steelwave.unonew.domain.entity.UserModel

object UserMapper {

    fun mapDbModelToEntity(user: UserDbModel) = UserModel(
        userId = user.userId,
        name = user.name,
        wins = user.wins,
        roundsWon = user.roundsWon,
        maxPoints = user.maxPoints,
        minPoints = user.minPoints,
        totalPoints = user.totalPoints
    )

    fun mapEntityToDbModel(user: UserModel) = UserDbModel(
        userId = user.userId,
        name = user.name,
        wins = user.wins,
        roundsWon = user.roundsWon,
        maxPoints = user.maxPoints,
        minPoints = user.minPoints,
        totalPoints = user.totalPoints
    )

    fun mapListDbModelToListEntity(userList: List<UserDbModel>) = userList.map {
        mapDbModelToEntity(it)
    }

    fun mapListEntityToListDbModel(userList: List<UserModel>) = userList.map {
        mapEntityToDbModel(it)
    }

}