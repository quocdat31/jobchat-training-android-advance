package com.example.androidadvance2

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.media.app.NotificationCompat
import com.example.androidadvance2.App.Companion.CHANNEL_ID


class MyMediaPlayer(var mediaPlayer: MediaPlayer?,
                    var context: Context,
                    var uri: Uri,
                    var seekBar: SeekBar,
                    var imageView: ImageView):
    Service(),
    MediaPlayer.OnCompletionListener {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCompletion(mp: MediaPlayer?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        var inputExtra = intent?.getStringExtra("inputExtra")

        if (inputExtra != null) {
            startMyForeground(inputExtra)
        }

        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
        return START_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun play(textView: TextView) {

            var myThread = MyThread(mediaPlayer, seekBar)
            var handler = Handler()
            var runnable = Runnable {
                mediaPlayer?.apply {
                    setAudioStreamType(AudioManager.STREAM_MUSIC)
                    setDataSource(context, uri)
                    prepare() // might take long! (for buffering, etc)
                    start()
                    seekBar.progress = currentPosition
                    seekBar.max = duration
                    textView.text = timeFormat(duration)
                }
            }

            imageView.setImageResource(R.drawable.ic_pause_black_24dp)
            handler.postDelayed(runnable,1000)
            myThread.start()

    }

    private fun startMyForeground(inputExtra: String) {

        val channelName = "My Background Service"
        val chan = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                channelName,
                NotificationManager.IMPORTANCE_NONE
            )
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager =
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)

        val notificationBuilder =
            androidx.core.app.NotificationCompat.Builder(this, CHANNEL_ID)
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            notificationBuilder.setOngoing(true)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setContentTitle(inputExtra)
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build()
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        startForeground(2, notification)
    }

    fun pause() {
        if (mediaPlayer != null)
            mediaPlayer?.pause()
    }

    fun stop() {
        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    fun seekTo(msec: Int) {
        mediaPlayer?.seekTo(msec)
    }

    fun getDuration(): Int? {
        return mediaPlayer?.duration
    }

}

class MyThread(var mediaPlayer: MediaPlayer?, var seekBar: SeekBar) : Thread() {
    override fun run() {
        while (true) {
            try {
                sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            if (mediaPlayer != null) {
                seekBar.post(Runnable { seekBar.progress = mediaPlayer!!.currentPosition })
            }
        }
    }
}