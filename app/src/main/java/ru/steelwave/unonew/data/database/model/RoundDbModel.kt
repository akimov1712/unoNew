package ru.steelwave.unonew.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import ru.steelwave.unonew.Const

@Entity(tableName = "round")
data class RoundDbModel(
    @PrimaryKey(autoGenerate = true)
    val roundId: Int = Const.UNDEFINED_ID,
    val gameId: Int,
    val numRoundInGame: Int,
    val scores: List<ScoreDbModel>,
)