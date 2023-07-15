package ru.steelwave.unonew.domain.entity

import ru.steelwave.unonew.Const
import ru.steelwave.unonew.data.database.model.ScoreDbModel
import java.util.*

data class GameModel(
    val gameId: Int = Const.UNDEFINED_ID,
    val participants: List<UserModel>,
    val targetPoints: Int,
    val leadingUser: ScoreModel,
    var creationDate: Long,
    var isFinished: Boolean = false,
    val rounds: List<RoundModel> = listOf(),
)