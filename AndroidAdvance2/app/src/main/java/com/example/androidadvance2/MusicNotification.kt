package com.example.androidadvance2

import android.app.*
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.drm.DrmStore
import android.graphics.BitmapFactory
import android.media.session.MediaSession
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.media.session.MediaButtonReceiver

class MusicNotification(
    var context: Context,
    var title: String,
    var artist: String
) : Notification() {

    private val builder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
        setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.screen_15))
        priority = NotificationCompat.PRIORITY_DEFAULT
        setCategory(NotificationCompat.CATEGORY_MESSAGE)
        setContentTitle(title)
        setContentText(artist)
        setDeleteIntent(
            MediaButtonReceiver.buildMediaButtonPendingIntent(
                context,
                PlaybackStateCompat.ACTION_STOP
            )
        )

        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        setSmallIcon(R.drawable.ic_music_note_black_24dp)
        color = ContextCompat.getColor(context, R.color.colorWhite)

        addAction(
            NotificationCompat.Action(
                R.drawable.ic_skip_previous_black_24dp,
                "PREVIOUS",
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    context,
                    PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
                )
            )
        )

        addAction(
            NotificationCompat.Action(
                R.drawable.ic_pause_black_24dp,
                "PAUSE",
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    context,
                    PlaybackStateCompat.ACTION_PLAY_PAUSE
                )
            )
        )



        addAction(
            NotificationCompat.Action(
                R.drawable.ic_skip_next_black_24dp,
                "NEXT",
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    context,
                    PlaybackStateCompat.ACTION_SKIP_TO_NEXT
                )
            )
        )

        setStyle(
            androidx.media.app.NotificationCompat.MediaStyle()
                .setMediaSession(setupMediaSession().sessionToken)
                .setShowActionsInCompactView(1)
                .setShowCancelButton(true)
                .setCancelButtonIntent(
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        context,
                        PlaybackStateCompat.ACTION_STOP
                    )
                )
        )
    }

    fun getNotification(): Notification {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var musicService =
                NotificationChannel(CHANNEL_ID, "test", NotificationManager.IMPORTANCE_DEFAULT)
            var notificationManager: NotificationManager =
                context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(musicService)
        }

        return builder.build()
    }

    private fun setupMediaSession(): MediaSessionCompat {
        var componentName = ComponentName(context.applicationContext, MediaButtonReceiver::class.java)
        var mediaButtonIntent = Intent(Intent.ACTION_MEDIA_BUTTON)
        mediaButtonIntent.component = componentName

        var pendingIntent = PendingIntent.getBroadcast(context.applicationContext,0,mediaButtonIntent,0)
        var mediaSessionCompat = MediaSessionCompat(context.applicationContext,"name", componentName, pendingIntent)
        mediaSessionCompat.setCallback(object: MediaSessionCompat.Callback() {
            override fun onPlay() {
                Log.d("asd","onplay")
                super.onPlay()
            }

            override fun onPause() {
                Log.d("asd","onpause")
                super.onPause()
            }

            override fun onSkipToNext() {
                super.onSkipToNext()
            }

            override fun onSkipToPrevious() {
                super.onSkipToPrevious()
            }

            override fun onStop() {
                super.onStop()
            }

            override fun onSeekTo(pos: Long) {
                super.onSeekTo(pos)
            }

            override fun onMediaButtonEvent(mediaButtonEvent: Intent?): Boolean {
                return super.onMediaButtonEvent(mediaButtonEvent)
            }
        })

        mediaSessionCompat.setFlags(0)

        mediaSessionCompat.setMediaButtonReceiver(pendingIntent)
        return mediaSessionCompat
    }

    companion object {
        val CHANNEL_ID = "musicServiceChannel"
    }



}