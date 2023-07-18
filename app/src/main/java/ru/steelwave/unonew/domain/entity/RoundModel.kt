package ru.steelwave.unonew.domain.entity

data class RoundModel(
    var roundId: Int,
    val scores: List<ScoreModel>,
)