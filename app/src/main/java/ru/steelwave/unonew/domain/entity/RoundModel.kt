package ru.steelwave.unonew.domain.entity

data class RoundModel(
    val roundId: Int,
    val scores: List<ScoreModel>,
)