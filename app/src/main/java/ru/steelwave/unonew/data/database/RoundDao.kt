package ru.steelwave.unonew.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.steelwave.unonew.data.database.model.RoundDbModel

@Dao
interface RoundDao {

    @Query("SELECT * FROM round WHERE gameId=:gameId ORDER BY numRoundInGame ASC")
    fun getRoundList(gameId: Int): LiveData<List<RoundDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRound(round: RoundDbModel)

}