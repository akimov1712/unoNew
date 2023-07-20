package ru.steelwave.unonew.domain.entity

import ru.steelwave.unonew.Const

data class RoundModel(
    var roundId: Int = Const.UNDEFINED_ID,
    val gameId: Int,
    val numRoundInGame: Int,
    val scores: List<ScoreModel>,
)