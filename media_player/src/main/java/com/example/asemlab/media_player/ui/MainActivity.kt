package com.example.asemlab.media_player.ui

import android.content.ComponentName
import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.example.asemlab.media_player.R
import com.example.asemlab.media_player.databinding.ActivityMainBinding
import com.example.asemlab.media_player.services.MediaPlayerService
import com.example.asemlab.media_player.utils.MediaPlayerUtils
import com.google.common.util.concurrent.MoreExecutors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaController: MediaController

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
                // TODO Use mediaController from MediaPlayerService instead of MediaPlayerUtils
                if (mediaController.isPlaying()) {
                    playButton.setImageResource(R.drawable.ic_play)
//                    MediaPlayerUtils.pause()
                    mediaController.pause()
                } else {
                    playButton.setImageResource(R.drawable.ic_pause)
//                    MediaPlayerUtils.play()
                    mediaController.play()
                }

            }

            stopButton.setOnClickListener {
//                MediaPlayerUtils.stop()
                mediaController.pause()
                mediaController.seekToDefaultPosition()
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

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")

        if (!::mediaController.isInitialized) {
            // TODO Create a SessionToken for MediaSession, then use the SessionToken to build a MediaController
            val sessionToken =
                SessionToken(this, ComponentName(this, MediaPlayerService::class.java))
            val controllerFuture = MediaController.Builder(this, sessionToken).buildAsync()
            controllerFuture.addListener(
                {
                    // Call controllerFuture.get() to retrieve the MediaController.
                    val uri = Uri.Builder()
                        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                        .path(R.raw.kmngang.toString())
                        .build()

                    controllerFuture.get().apply {
                        setMediaItem(MediaItem.fromUri(uri))
                        // TODO Add a listener for the MediaController (Optional)
                        addListener(object : Player.Listener {

                            override fun onIsPlayingChanged(isPlaying: Boolean) {
                                super.onIsPlayingChanged(isPlaying)
                                if (isPlaying) {
                                    binding.playButton.setImageResource(R.drawable.ic_pause)
                                } else {
                                    binding.playButton.setImageResource(R.drawable.ic_play)
                                }
                            }
                        })
                        mediaController = this
//                binding.playView.setPlayer(controllerFuture.get())
                    }
                },
                MoreExecutors.directExecutor()
            )
        }
    }
}