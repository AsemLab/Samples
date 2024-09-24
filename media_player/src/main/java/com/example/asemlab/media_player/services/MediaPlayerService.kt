package com.example.asemlab.media_player.services

import androidx.media3.common.AudioAttributes
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import java.util.UUID

class MediaPlayerService : MediaSessionService() {

    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()
        if (mediaSession == null) {
            val player = ExoPlayer.Builder(this)
                .setHandleAudioBecomingNoisy(true)
                .setAudioAttributes(
                    AudioAttributes.DEFAULT,
                    true
                ) // Handle audio focus automatically
                .build()
            mediaSession =
                MediaSession.Builder(this, player).setId(UUID.randomUUID().toString()).build()
        }
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
        mediaSession

}