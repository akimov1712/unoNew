package ru.steelwave.unonew.data.database.model

import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
data class ScoreDbModel(
    val user: UserDbModel,
    val countPoints: Int
)