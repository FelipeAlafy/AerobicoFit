package com.github.felipealafy.aerobicofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.felipealafy.aerobicofit.databinding.ActivityMainBinding
import com.github.felipealafy.aerobicofit.model.FakeVideoList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var workoutView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val workoutsAdapter = WorkoutsAdapter(FakeVideoList().getListOfFakeVideos())
        workoutView = binding.workouts
        workoutView.adapter = workoutsAdapter

        workoutsAdapter.onItemClick = {
            val intent = Intent(this, VideoActivity::class.java)
            intent.putExtra("Video", it)
            startActivity(intent)
        }
    }
}