package com.example.androidadvance2

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import com.example.androidadvance2.model.Songs


class MyMediaPlayer :
    Service(),
    MediaPlayer.OnCompletionListener {

    lateinit var mediaPlayer: MediaPlayer
    lateinit var songs: Songs
    var seekBar: SeekBar? = null
    var textView: TextView? = null

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

        if (intent == null) return START_STICKY

        var songs = intent.getParcelableExtra<Songs>("SONG")
        var myNotification = baseContext?.let { MyNotification(it, songs.title, songs.artist) }

        startForeground(1, myNotification?.getNotification())
        return START_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
    }

    fun play(seekBar: SeekBar, songs: Songs, textView: TextView, context: Context) {
        var mediaPlayer = MediaPlayer()
        var myThread = seekBar?.let { MyThread(mediaPlayer, it) }
        var handler = Handler()
        var runnable = Runnable {
            mediaPlayer?.apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(context, songs.uri)
                prepare()
                start()
                seekBar?.apply {
                    progress = currentPosition
                    max = duration
                }
                textView?.text = timeFormat(duration)
                Log.d("asd:", "$duration")
            }
        }

        //imageView?.setImageResource(R.drawable.ic_pause_black_24dp)
        handler.postDelayed(runnable, 1000)
        myThread?.start()

    }

    fun pause() {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.pause()
    }

    fun stop() {
        mediaPlayer?.release()
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
                seekBar.progress = mediaPlayer?.currentPosition?.div(1000) ?: 0
                seekBar.post(Runnable { seekBar.progress = mediaPlayer!!.currentPosition })
            }
        }
    }
}