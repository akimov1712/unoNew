package ru.steelwave.unonew.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import ru.steelwave.unonew.Const

@Serializable
@Entity(tableName = "user")
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = Const.UNDEFINED_ID,
    val name: String,
    var wins: Int = 0,
    var roundsWon: Int = 0,
    var maxPoints: Int = 0,
    var minPoints: Int = 0,
    var totalPoints: Int = 0,
)