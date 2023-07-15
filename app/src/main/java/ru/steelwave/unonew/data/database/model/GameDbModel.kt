package ru.steelwave.unonew.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import ru.steelwave.unonew.Const

@Serializable
@Entity(tableName = "game")
data class GameDbModel(
    @PrimaryKey(autoGenerate = true)
    val gameId: Int = Const.UNDEFINED_ID,
    val participants: List<UserDbModel>,
    val targetPoints: Int,
    val leadingUser: ScoreDbModel,
    var creationDate: Long,
    var isFinished: Boolean = false,
    val rounds: MutableList<RoundDbModel> = mutableListOf()
)