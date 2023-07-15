package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.steelwave.unonew.data.database.model.UserDbModel

class UserConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String): UserDbModel {
        return gson.fromJson(value, UserDbModel::class.java)
    }

    @TypeConverter
    fun toString(userModel: UserDbModel): String {
        return gson.toJson(userModel)
    }

}