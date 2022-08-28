package uz.gita.memorygame.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard")
data class LeaderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val duration: String = "",
    val time: Long = System.currentTimeMillis(),
    val drawingTime: Long = 0L,
    val category: String
)
