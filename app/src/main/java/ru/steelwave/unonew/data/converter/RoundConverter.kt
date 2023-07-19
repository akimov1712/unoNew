package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.steelwave.unonew.data.database.model.RoundDbModel
import ru.steelwave.unonew.data.database.model.ScoreDbModel

class RoundConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): RoundDbModel {
        return gson.fromJson(value, RoundDbModel::class.java)
    }

    @TypeConverter
    fun toString(rounds: RoundDbModel): String {
        return gson.toJson(rounds)
    }
}
