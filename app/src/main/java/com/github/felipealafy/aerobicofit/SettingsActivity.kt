package com.github.felipealafy.aerobicofit

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.felipealafy.aerobicofit.databinding.ActivitySettingsBinding
import com.github.felipealafy.aerobicofit.model.Video
import com.github.felipealafy.aerobicofit.util.DataManager.Companion.setNewConfigs
import com.github.felipealafy.aerobicofit.util.DataManager.Companion.previousExerciseTimerSet
import com.github.felipealafy.aerobicofit.util.DataManager.Companion.previousRestTimerSet
import com.github.felipealafy.aerobicofit.util.DataManager.Companion.previousSetsSet

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar.root)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setData()
        class seekListener: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (!fromUser) return
                if (seekBar == null) return
                when(resources.getResourceEntryName(seekBar.id)) {
                    "exercise_timer_length_seekbar" -> {
                        binding.exerciseTimerLengthText.text = "${binding.exerciseTimerLengthSeekbar.progress} s"
                    }
                    "rest_timer_length_seekbar" -> {
                        binding.restTimerLengthText.text = "${binding.restTimerLengthSeekbar.progress} s"
                    }
                    "amount_of_sets_seekbar" -> {
                        binding.amountOfSetsText.text = "${binding.amountOfSetsSeekbar.progress}"
                    }
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        }

        val listener = seekListener()
        binding.exerciseTimerLengthSeekbar.setOnSeekBarChangeListener(listener)
        binding.restTimerLengthSeekbar.setOnSeekBarChangeListener(listener)
        binding.amountOfSetsSeekbar.setOnSeekBarChangeListener(listener)

    }

    fun setData() {
        binding.exerciseTimerLengthText.text = "${applicationContext.previousExerciseTimerSet() / 1000} s"
        binding.exerciseTimerLengthSeekbar.progress = (applicationContext.previousExerciseTimerSet() / 1000).toInt()

        binding.restTimerLengthText.text = "${applicationContext.previousRestTimerSet() / 1000} s"
        binding.restTimerLengthSeekbar.progress = (applicationContext.previousRestTimerSet() / 1000).toInt()

        binding.amountOfSetsText.text = "${applicationContext.previousSetsSet()}"
        binding.amountOfSetsSeekbar.progress = applicationContext.previousSetsSet()
    }

    fun save(view: View) {
        val r = applicationContext.setNewConfigs(Video("", "", limitSets = binding.amountOfSetsSeekbar.progress, setTime =
        (binding.exerciseTimerLengthSeekbar.progress * 1000).toLong(), restTime =
        (binding.restTimerLengthSeekbar.progress * 1000).toLong()))

        if (r) {
            Toast.makeText(this, "Gravado.", Toast.LENGTH_SHORT).show()
        }
    }
}