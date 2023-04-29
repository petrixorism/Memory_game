package uz.ravshanbaxranov.memorygame.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LeaderEntity::class], version = 1)
abstract class LeaderDatabase : RoomDatabase() {

    abstract fun getDao(): LeaderboardDao


    companion object {
        private var INSTANCE: LeaderDatabase? = null

        fun init(context: Context): LeaderDatabase {
            val temp = INSTANCE
            if (temp != null) return temp

            val instance = Room.databaseBuilder(context, LeaderDatabase::class.java, "news.db")
                .allowMainThreadQueries()
                .build()

            INSTANCE = instance
            return instance
        }
    }

}