package com.example.asemlab.media_player.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.asemlab.media_player.R
import com.example.asemlab.media_player.databinding.ActivityMainBinding
import com.example.asemlab.media_player.utils.MediaPlayerUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        MediaPlayerUtils.init(this)
        MediaPlayerUtils.setMediaItem(R.raw.kmngang)

        with(binding) {
            playButton.setOnClickListener {
                if (MediaPlayerUtils.isPlaying()) {
                    playButton.setImageResource(R.drawable.ic_play)
                    MediaPlayerUtils.pause()
                } else {
                    playButton.setImageResource(R.drawable.ic_pause)
                    MediaPlayerUtils.play()
                }

            }

            stopButton.setOnClickListener {
                MediaPlayerUtils.stop()
                playButton.setImageResource(R.drawable.ic_play)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (MediaPlayerUtils.isPlaying()) {
            binding.playButton.setImageResource(R.drawable.ic_play)
            MediaPlayerUtils.pause()
        }
    }
}