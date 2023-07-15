package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import androidx.room.util.TableInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.steelwave.unonew.data.database.model.UserDbModel
import java.util.*

class DateConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}