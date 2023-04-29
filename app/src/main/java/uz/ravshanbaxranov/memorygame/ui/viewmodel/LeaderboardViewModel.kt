package uz.ravshanbaxranov.memorygame.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.ravshanbaxranov.memorygame.data.LeaderEntity
import uz.ravshanbaxranov.memorygame.data.LeaderboardRepository
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