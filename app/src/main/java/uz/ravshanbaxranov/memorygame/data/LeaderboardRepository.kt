package uz.ravshanbaxranov.memorygame.data

import javax.inject.Inject

class LeaderboardRepository @Inject constructor(private val dao: LeaderboardDao) {

    fun insertLeaderBoard(leaderEntity: LeaderEntity) = dao.insert(leaderEntity)


    fun deleteLeaderboard(leaderEntity: LeaderEntity) = dao.delete(leaderEntity)


    fun getLeaderBoardList(category: String) = dao.getLeaderBoardsList(category)


}