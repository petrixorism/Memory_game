package uz.gita.memorygame.data

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LeaderboardRepository @Inject constructor(val db: LeaderboardDao) {

    fun insertLeaderBoard(leaderEntity: LeaderEntity) = flow {
        db.insert(leaderEntity)
        emit(Unit)
    }

    fun deleteLeaderboard(leaderEntity: LeaderEntity) = flow {
        db.insert(leaderEntity)
        emit(Unit)
    }

    fun getLeaderBoard() = flow {
        db.getLeaderBoards().collect() {
            emit(it)
        }
    }

}