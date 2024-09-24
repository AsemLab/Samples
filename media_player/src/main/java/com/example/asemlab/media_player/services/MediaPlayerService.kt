package com.example.asemlab.media_player.services

import android.os.Bundle
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.CommandButton
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.session.SessionCommand
import androidx.media3.session.SessionResult
import com.example.asemlab.media_player.R
import com.example.asemlab.media_player.utils.CommandActions.ACTION_BACK_15_SECS
import com.example.asemlab.media_player.utils.CommandActions.ACTION_FORWARD_15_SECS
import com.google.common.collect.ImmutableList
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import java.util.UUID

class MediaPlayerService : MediaSessionService() {

    // TODO 1. Create SessionCommands
    private val customCommandBack15 = SessionCommand(ACTION_BACK_15_SECS, Bundle.EMPTY)
    private val customCommandForward15 = SessionCommand(ACTION_FORWARD_15_SECS, Bundle.EMPTY)

    private var mediaSession: MediaSession? = null

    @OptIn(UnstableApi::class)
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

            // TODO 2. Create CommandButtons
            val back15Button =
                CommandButton.Builder()
                    .setDisplayName("Back 15 seconds")
                    .setIconResId(R.drawable.ic_replay_10)
                    .setSessionCommand(customCommandBack15)
                    .build()

            val forward15Button =
                CommandButton.Builder()
                    .setDisplayName("Forward 15 seconds")
                    .setIconResId(R.drawable.ic_forward_10)
                    .setSessionCommand(customCommandForward15)
                    .build()

            // TODO 6. Add created CommandButtons & implemented Callback
            mediaSession =
                MediaSession.Builder(this, player).setId(UUID.randomUUID().toString())
                    .setCallback(MyCallback())
                    .setCustomLayout(ImmutableList.of(back15Button, forward15Button)).build()
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

    // TODO 3. Implement MediaSession.Callback
    private inner class MyCallback : MediaSession.Callback {

        @OptIn(UnstableApi::class)
        override fun onConnect(
            session: MediaSession,
            controller: MediaSession.ControllerInfo
        ): MediaSession.ConnectionResult {

            // Set available player and session commands.
            return MediaSession.ConnectionResult.AcceptedResultBuilder(session)
                // TODO You can remove default player buttons
//                .setAvailablePlayerCommands(
//                    ConnectionResult.DEFAULT_PLAYER_COMMANDS.buildUpon()
//                        .remove(COMMAND_SEEK_TO_PREVIOUS)
//                        .build()
//                )
                // TODO 4. Add Created SessionCommands
                .setAvailableSessionCommands(
                    MediaSession.ConnectionResult.DEFAULT_SESSION_COMMANDS.buildUpon()
                        .add(customCommandBack15)
                        .add(customCommandForward15)
                        .build()
                )
                .build()
        }

        override fun onCustomCommand(
            session: MediaSession,
            controller: MediaSession.ControllerInfo,
            customCommand: SessionCommand,
            args: Bundle
        ): ListenableFuture<SessionResult> {
            // TODO 5. Add commands logic
            return when (customCommand.customAction) {
                ACTION_BACK_15_SECS -> {
                    session.player.seekBack()
                    Futures.immediateFuture(SessionResult(SessionResult.RESULT_SUCCESS))
                }

                ACTION_FORWARD_15_SECS -> {
                    session.player.seekForward()
                    Futures.immediateFuture(SessionResult(SessionResult.RESULT_SUCCESS))
                }

                else -> super.onCustomCommand(session, controller, customCommand, args)
            }
        }
    }
}