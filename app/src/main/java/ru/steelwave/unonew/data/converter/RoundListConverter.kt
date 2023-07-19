package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.steelwave.unonew.data.database.model.RoundDbModel

class RoundListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<RoundDbModel> {
        val type = object : TypeToken<List<RoundDbModel>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun toString(rounds: List<RoundDbModel>): String {
        return gson.toJson(rounds)
    }
}
