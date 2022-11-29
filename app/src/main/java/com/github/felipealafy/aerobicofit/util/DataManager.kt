package com.github.felipealafy.aerobicofit.util

import android.content.Context
import androidx.preference.PreferenceManager
import com.github.felipealafy.aerobicofit.model.Video

class DataManager {
    companion object {
        private const val EXERCISE_TIMER_ID = "com.github.felipealafy.exercise_timer"
        private const val REST_TIMER_ID = "com.github.felipealafy.rest_timer"
        private const val SETS_TIMER_ID = "com.github.felipealafy.sets_timer"

        fun Context.firstTimeRunning() {
            if (this.isFirstTimeRunning()) return
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putLong(EXERCISE_TIMER_ID, 40000)
                .putLong(REST_TIMER_ID, 20000)
                .putInt(SETS_TIMER_ID, 4)
                .apply()
        }

        private fun Context.isFirstTimeRunning() = PreferenceManager.getDefaultSharedPreferences(this).contains(
            EXERCISE_TIMER_ID)

        fun Context.previousExerciseTimerSet() =
            PreferenceManager.getDefaultSharedPreferences(this).getLong(EXERCISE_TIMER_ID, 40000)

        fun Context.previousRestTimerSet() =
            PreferenceManager.getDefaultSharedPreferences(this).getLong(REST_TIMER_ID, 20000)

        fun Context.previousSetsSet() =
            PreferenceManager.getDefaultSharedPreferences(this).getInt(SETS_TIMER_ID, 4)

        fun Context.setNewConfigs(video: Video): Boolean {
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                .putLong(EXERCISE_TIMER_ID, video.setTime)
                .putLong(REST_TIMER_ID, video.restTime)
                .putInt(SETS_TIMER_ID, video.limitSets)
                .apply()
            return true
        }
    }
}