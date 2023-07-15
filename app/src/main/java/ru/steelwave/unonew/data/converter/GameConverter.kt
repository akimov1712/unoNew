package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.steelwave.unonew.data.database.model.UserDbModel

class GameConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): List<UserDbModel> {
        val listType = object : TypeToken<List<UserDbModel>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toString(participants: List<UserDbModel>): String {
        return gson.toJson(participants)
    }

}