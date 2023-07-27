package ru.steelwave.unonew.domain.entity

import ru.steelwave.unonew.Const

data class UserModel(
    val userId: Int = Const.UNDEFINED_ID,
    val name: String,
    var wins: Int = 0,
    var roundsWon: Int = 0,
    var maxPoints: Int = -1,
    var minPoints: Int = -1,
    var totalPoints: Int = -1,
)