package com.github.felipealafy.aerobicofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.github.felipealafy.aerobicofit.databinding.ActivityMainBinding
import com.github.felipealafy.aerobicofit.model.Videos

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var workoutView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val workoutsAdapter = WorkoutsAdapter(Videos().getListOfVideos())
        workoutView = binding.workouts
        workoutView.adapter = workoutsAdapter

        workoutsAdapter.onItemClick = {
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra("Video", it)
            startActivity(intent)
        }
    }
}