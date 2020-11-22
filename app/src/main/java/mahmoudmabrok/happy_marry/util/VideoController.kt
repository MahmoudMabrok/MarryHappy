package edu.mahmoud_mabrouk.happy_marry.util

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class VideoController(private val video_view: PlayerView) {
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    var player: ExoPlayer? = null


    fun initializePlayer(ctx: Context, url: String?) {
        player = SimpleExoPlayer.Builder(ctx).build()
        video_view.player = player
        val mediaSource = buildMediaSource(ctx, Uri.parse(url ?: ""))
        player?.playWhenReady = playWhenReady
        player?.seekTo(currentWindow, playbackPosition)
        player?.prepare(mediaSource, true, true)
    }

    private fun buildMediaSource(ctx: Context, uri: Uri): MediaSource {
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(ctx, "exoplayer-codelab")

        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(uri)
    }

    fun releasePlayer() {
        if (player != null) {
            playWhenReady = player!!.playWhenReady
            playbackPosition = player!!.currentPosition
            currentWindow = player!!.currentWindowIndex
            player!!.release()
            player = null
        }
    }

}