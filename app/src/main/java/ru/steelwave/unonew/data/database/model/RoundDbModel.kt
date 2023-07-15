package ru.steelwave.unonew.data.database.model

import kotlinx.serialization.Serializable

@Serializable
data class RoundDbModel(
    val scores: List<ScoreDbModel>,
)