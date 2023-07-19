package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.steelwave.unonew.data.database.model.ScoreDbModel

class ScoreListConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<ScoreDbModel> {
        val type = object : TypeToken<List<ScoreDbModel>>(){}.type
        return gson.fromJson(value, type) ?: emptyList()
    }

    @TypeConverter
    fun toString(scoreModel: List<ScoreDbModel>): String {
        return gson.toJson(scoreModel)
    }

}