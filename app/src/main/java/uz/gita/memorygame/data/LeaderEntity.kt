package uz.gita.memorygame.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard")
data class LeaderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val time: Long,
    val duration: Long = System.currentTimeMillis()
)
