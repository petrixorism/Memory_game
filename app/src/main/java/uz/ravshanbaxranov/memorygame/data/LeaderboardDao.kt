package uz.ravshanbaxranov.memorygame.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaderboardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(leaderEntity: LeaderEntity)

    @Delete
    fun delete(leaderEntity: LeaderEntity)

    @Query("SELECT * FROM leaderboard ORDER BY drawingTime ASC")
    fun getLeaderBoards(): Flow<List<LeaderEntity>>

    @Query("SELECT * FROM leaderboard WHERE category=:category ORDER BY drawingTime ASC")
    fun getLeaderBoardsList(category: String): List<LeaderEntity>


}