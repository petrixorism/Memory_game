package uz.gita.memorygame.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.memorygame.data.LeaderEntity
import uz.gita.memorygame.data.LeaderboardRepository
import java.util.*
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    private val repository: LeaderboardRepository
) : ViewModel() {


    private val _leaderBoardStateFlow = MutableStateFlow<List<LeaderEntity>>(emptyList())
    val leaderBoardFlow: Flow<List<LeaderEntity>> = _leaderBoardStateFlow.asStateFlow()

    fun getLeaderboardByCategory(category: String) {
        viewModelScope.launch {
            repository.getLeaderBoardList(category).apply {
                _leaderBoardStateFlow.emit(this)
            }
        }
    }

}