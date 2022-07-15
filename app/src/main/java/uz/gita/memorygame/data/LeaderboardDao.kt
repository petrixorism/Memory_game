package uz.gita.memorygame.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaderboardDao {

    @Insert
    fun insert(leaderEntity: LeaderEntity)

    @Delete
    fun delete(leaderEntity: LeaderEntity)

    @Query("SELECT * FROM leaderboard ORDER BY duration")
    suspend fun getLeaderBoards(): Flow<List<LeaderEntity>>

}