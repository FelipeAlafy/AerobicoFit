package com.github.felipealafy.aerobicofit

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.felipealafy.aerobicofit.databinding.ActivityVideoBinding
import com.github.felipealafy.aerobicofit.model.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val video = intent.getParcelableExtra<Video>("Video")
        if (video != null) {
            val videoView = binding.videoView
            lifecycle.addObserver(videoView)

            videoView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(video.link, 0f)
                }
            })

            binding.videoTitle.text = video.title
            binding.videoDescription.text = video.desc
        }
    }

    private fun String.getURI() = Uri.parse(this)
}