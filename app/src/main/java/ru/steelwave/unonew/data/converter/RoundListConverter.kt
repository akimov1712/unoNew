package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.steelwave.unonew.data.database.model.RoundDbModel

class RoundListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<RoundDbModel> {
        return try {
            val type = object : TypeToken<List<RoundDbModel>>() {}.type
            gson.fromJson(value, type) ?: emptyList()
        } catch (e: Exception) {
            throw RuntimeException(e.toString())
        }
    }

    @TypeConverter
    fun toString(rounds: List<RoundDbModel>): String {
        return gson.toJson(rounds)
    }
}
