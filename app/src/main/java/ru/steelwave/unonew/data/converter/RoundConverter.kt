package ru.steelwave.unonew.data.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ru.steelwave.unonew.data.database.model.RoundDbModel

class RoundConverter {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val roundAdapter: JsonAdapter<RoundDbModel> = moshi.adapter(RoundDbModel::class.java)

    @TypeConverter
    fun fromString(value: String): List<RoundDbModel> {
        return try {
            val type = Types.newParameterizedType(List::class.java, RoundDbModel::class.java)
            val adapter: JsonAdapter<List<RoundDbModel>> = moshi.adapter(type)
            adapter.fromJson(value) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun toString(rounds: List<RoundDbModel>): String {
        return try {
            val type = Types.newParameterizedType(List::class.java, RoundDbModel::class.java)
            val adapter: JsonAdapter<List<RoundDbModel>> = moshi.adapter(type)
            adapter.toJson(rounds)
        } catch (e: Exception) {
            ""
        }
    }
}
