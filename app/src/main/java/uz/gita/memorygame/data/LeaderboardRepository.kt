package uz.gita.memorygame.data

import android.util.Log
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(val dao: LeaderboardDao) {

    fun insertLeaderBoard(leaderEntity: LeaderEntity) {
        dao.insert(leaderEntity)

    }

    fun deleteLeaderboard(leaderEntity: LeaderEntity) {
        dao.delete(leaderEntity)
    }

    fun getLeaderBoardList(category: String) = dao.getLeaderBoardsList(category)


}