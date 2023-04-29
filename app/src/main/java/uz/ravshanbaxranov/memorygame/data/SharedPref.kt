package uz.ravshanbaxranov.memorygame.data

import android.content.Context

class SharedPref(context: Context) {

    companion object {

        private var instance: SharedPref? = null

        fun init(context: Context) {
            instance = SharedPref(context)
        }

        fun getInstance(): SharedPref = instance!!

    }

    private val pref = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE)


    var nameEasy: String
        set(value) = pref.edit().putString("NAME_EASY", value).apply()
        get() = pref.getString("NAME_EASY", "")!!

    var nameMiddle: String
        set(value) = pref.edit().putString("NAME_MIDDLE", value).apply()
        get() = pref.getString("NAME_MIDDLE", "")!!

    var nameHard: String
        set(value) = pref.edit().putString("NAME_HARD", value).apply()
        get() = pref.getString("NAME_HARD", "")!!


    var durationEasy: String
        set(value) = pref.edit().putString("DURATION_EASY", value).apply()
        get() = pref.getString("DURATION_EASY", "")!!

    var durationMiddle: String
        set(value) = pref.edit().putString("DURATION_MIDDLE", value).apply()
        get() = pref.getString("DURATION_MIDDLE", "")!!

    var durationHard: String
        set(value) = pref.edit().putString("DURATION_HARD", value).apply()
        get() = pref.getString("DURATION_HARD", "")!!


    var drawingTimeEasy: Long
        set(value) = pref.edit().putLong("DRAWING_TIME_EASY", value).apply()
        get() = pref.getLong("DRAWING_TIME_EASY", 0L)

    var drawingTimeMiddle: Long
        set(value) = pref.edit().putLong("DRAWING_TIME_MIDDLE", value).apply()
        get() = pref.getLong("DRAWING_TIME_MIDDLE", 0L)

    var drawingTimeHard: Long
        set(value) = pref.edit().putLong("DRAWING_TIME_HARD", value).apply()
        get() = pref.getLong("DRAWING_TIME_HARD", 0L)


    var dateEasy: Long
        set(value) = pref.edit().putLong("DATE_EASY", value).apply()
        get() = pref.getLong("DATE_EASY", 0L)

    var dateMiddle: Long
        set(value) = pref.edit().putLong("DATE_MIDDLE", value).apply()
        get() = pref.getLong("DATE_MIDDLE", 0L)

    var dateHard: Long
        set(value) = pref.edit().putLong("DATE_HARD", value).apply()
        get() = pref.getLong("DATE_HARD", 0L)


}