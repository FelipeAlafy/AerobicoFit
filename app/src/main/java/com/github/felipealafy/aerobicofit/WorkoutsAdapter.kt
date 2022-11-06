package com.github.felipealafy.aerobicofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.felipealafy.aerobicofit.model.Video

class WorkoutsAdapter(videoList: List<Video>):
    RecyclerView.Adapter<WorkoutsAdapter.VideoViewHolder>() {

    var onItemClick: ((Video) -> Unit)? = null

    private val videos = videoList

    class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        init {
            title = view.findViewById(R.id.title)
        }

        fun bind(video: Video) {
            title.text = video.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workout, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videos[position]
        holder.bind(video)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(video)
        }
    }

    override fun getItemCount() = videos.size
}