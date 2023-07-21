package ru.steelwave.unonew.domain.entity

import ru.steelwave.unonew.Const

data class RoundModel(
    var roundId: Int = Const.UNDEFINED_ID,
    val gameId: Int,
    var numRoundInGame: Int,
    var scores: List<ScoreModel>,
)