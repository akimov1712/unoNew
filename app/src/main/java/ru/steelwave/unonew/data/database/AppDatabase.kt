package ru.steelwave.unonew.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.steelwave.unonew.data.converter.*
import ru.steelwave.unonew.data.database.model.GameDbModel
import ru.steelwave.unonew.data.database.model.UserDbModel

@Database(entities = [UserDbModel::class, GameDbModel::class], version = 2, exportSchema = false)
@TypeConverters(
    UserConverter::class,
    RoundListConverter::class,
    RoundConverter::class,
    ScoreListConverter::class,
    ScoreConverter::class,
    DateConverter::class,
    GameConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun gameDao(): GameDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "test.db"
        private val LOCK = Any()

        fun getInstance(application: Application): AppDatabase{
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}