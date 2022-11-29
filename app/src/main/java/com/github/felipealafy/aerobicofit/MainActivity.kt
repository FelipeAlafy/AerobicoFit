package com.github.felipealafy.aerobicofit

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.felipealafy.aerobicofit.databinding.ActivityMainBinding
import com.github.felipealafy.aerobicofit.model.ExampleVideos
import com.github.felipealafy.aerobicofit.model.Video
import com.github.felipealafy.aerobicofit.model.Videos
import com.github.felipealafy.aerobicofit.model.VideosList
import com.github.felipealafy.aerobicofit.util.DataManager.Companion.firstTimeRunning
import com.github.felipealafy.aerobicofit.util.DataManager.Companion.previousExerciseTimerSet
import com.github.felipealafy.aerobicofit.util.DataManager.Companion.previousRestTimerSet
import com.github.felipealafy.aerobicofit.util.DataManager.Companion.previousSetsSet

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var workoutView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applicationContext.firstTimeRunning()

        val videoListManager: VideosList = Videos()
        val workoutsAdapter = WorkoutsAdapter(videoListManager.getListOfVideos())
        workoutView = binding.workouts
        workoutView.adapter = workoutsAdapter

        setSupportActionBar(binding.toolbar.root)

        workoutsAdapter.onItemClick = {
            val intent = Intent(this, VideoActivity::class.java)
            //Loading previous data
            it.setTime = applicationContext.previousExerciseTimerSet()
            it.restTime = applicationContext.previousRestTimerSet()
            it.limitSets = applicationContext.previousSetsSet()
            intent.putExtra("Video", it)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.default_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.credits -> {
                val intent = Intent(this, CreditsActicity::class.java)
                startActivity(intent)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
}