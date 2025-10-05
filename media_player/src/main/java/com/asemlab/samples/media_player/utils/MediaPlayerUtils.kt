package com.asemlab.samples.media_player.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.annotation.RawRes
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

// TODO Implement Media Player
object MediaPlayerUtils {

    private lateinit var player: ExoPlayer

    fun init(context: Context) {
        player = ExoPlayer.Builder(context).setHandleAudioBecomingNoisy(true).build()
    }

    fun setMediaItem(@RawRes id: Int) {
        if (isPlayerInitialized()) {
            with(player) {
                val uri =
                    Uri.Builder().scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                        .path(id.toString())
                        .build()
                setMediaItem(MediaItem.fromUri(uri))
            }
        }
    }

    fun release() {
        if (isPlayerInitialized()) {
            player.release()
        }
    }

    fun play() {
        if (isPlayerInitialized() && !isPlaying()) {
            player.prepare()
            player.play()
        }
    }

    fun pause() {
        if (isPlayerInitialized() && isPlaying()) {
            player.pause()
        }
    }

    fun stop() {
        if (isPlayerInitialized()) {
            player.seekToDefaultPosition()
            player.pause()
        }
    }

    fun isPlaying() = isPlayerInitialized() && player.isPlaying

    fun isPlayerInitialized() = ::player.isInitialized

    fun seekTo(progress: Long) {
        if (isPlayerInitialized()) {
            player.seekTo(progress)
        }
    }

    fun getMediaLong(): Long = player.duration

}