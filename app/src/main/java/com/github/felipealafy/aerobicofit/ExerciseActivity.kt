package com.github.felipealafy.aerobicofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.github.felipealafy.aerobicofit.databinding.ActivityExerciseBinding
import com.github.felipealafy.aerobicofit.model.Video
import com.github.felipealafy.aerobicofit.util.ExerciseState
import com.github.felipealafy.aerobicofit.util.TimerState

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private lateinit var cd: CountDownTimer
    private lateinit var video: Video
    private var timeRemaining: Long = 0
    private var exerciseState: ExerciseState = ExerciseState.FREETIME
    private var state: TimerState = TimerState.STOPPED
    private var limitSets: Int = 1
    private var currentSet: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.root)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        video = intent.getParcelableExtra("Video")!!

        limitSets = video.limitSets
        timeRemaining = video.setTime
        updateUi()

        goToExercise()
        binding.exerciseStatus.text = getSetString()

        binding.start.setOnClickListener {
            if (state == TimerState.PAUSED || state == TimerState.STOPPED) {
                start()
            } else {
                pause()
            }
        }
    }

    inner class CountDown(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture,
        countDownInterval) {
        override fun onTick(p0: Long) {
            timeRemaining = p0 //This came from exercise class, it only can be called because of inner classification on the class
            updateUi() //This came from exercise class, it only can be called because of inner classification on the class
        }

        override fun onFinish() {
            state = TimerState.STOPPED
            if (currentSet == limitSets) onFinishTimer()
            if (exerciseState == ExerciseState.FREETIME && currentSet <= limitSets) {
                currentSet++
                binding.exerciseStatus.text = getSetString()
                goToExercise()
            } else if (exerciseState == ExerciseState.RUNNING && currentSet < limitSets) {
                goToFreeTime()
            }
        }
    }

    private fun start() {
        state = TimerState.RUNNING
        cd = CountDown(timeRemaining, 1000).start()
        binding.start.text = getString(R.string.pause)
    }

    private fun pause() {
        state = TimerState.PAUSED
        cd.cancel()
        binding.start.text = getString(R.string.resume)
    }

    private fun onFinishTimer() {
        state = TimerState.STOPPED
        Toast.makeText(this, getString(R.string.end_sets), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi() {
        val min: Int = ((timeRemaining / 1000) / 60).toInt()
        val sec: Int = ((timeRemaining / 1000) % 60).toInt()
        binding.timer.text = "%02d:%02d".format(min, sec)
    }

    private fun goToFreeTime() {
        binding.exerciseStatus.text = getString(R.string.rest)
        binding.exerciseStatus.setTextColor(resources.getColor(R.color.green, theme))
        exerciseState = ExerciseState.FREETIME
        timeRemaining = video.restTime
        start()
    }

    private fun goToExercise() {
        binding.exerciseStatus.setTextColor(resources.getColor(R.color.red, theme))
        exerciseState = ExerciseState.RUNNING
        timeRemaining = video.setTime
        start()
    }

    private fun getSetString() =
        "${getString(R.string.set)} $currentSet ${getString(R.string.set_delimiter)} $limitSets"
}