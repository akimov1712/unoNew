package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.steelwave.unonew.data.database.model.ScoreDbModel

class ScoreConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): ScoreDbModel {
        return gson.fromJson(value, ScoreDbModel::class.java)
    }

    @TypeConverter
    fun toString(scoreModel: ScoreDbModel): String {
        return gson.toJson(scoreModel)
    }

}