package uz.ravshanbaxranov.memorygame.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import uz.ravshanbaxranov.memorygame.data.LeaderEntity
import uz.ravshanbaxranov.memorygame.data.LeaderboardRepository
import javax.inject.Inject

@HiltViewModel
class PlaygroundViewModel @Inject constructor(
    private val repository: LeaderboardRepository
) : ViewModel() {

    private val _insertedLeaderboardChannel = Channel<Unit>()
    private val _isTopUserChannel = Channel<Boolean>()

    val insertedFlow: Flow<Unit> = _insertedLeaderboardChannel.receiveAsFlow()
    val isTopUser: Flow<Boolean> = _isTopUserChannel.receiveAsFlow()


    fun finishGame(drawingTime: Long, category: String) {
        viewModelScope.launch {
            repository.getLeaderBoardList(category).apply {
                Log.d("TAGDF", "finishGame: $drawingTime")
                if (this.isEmpty()) {
                    _isTopUserChannel.send(true)
                } else {
                    if (this.size < 10) {
                        _isTopUserChannel.send(true)
                    } else {
                        if (this.last().drawingTime > drawingTime) {
                            _isTopUserChannel.send(true)
                        } else {
                            _isTopUserChannel.send(false)
                        }
                    }
                }
            }
        }
    }

    fun insertUserToLeaderboard(leaderEntity: LeaderEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLeaderBoardList(leaderEntity.category).apply {
                if (this.isNotEmpty()) {
                    if (this.size > 9) {
                        Log.d("TAGDF", "deleted:")
                        repository.deleteLeaderboard(this.last())
                    }
                }

                repository.insertLeaderBoard(leaderEntity)
                _insertedLeaderboardChannel.send(Unit)

            }
        }
    }


}