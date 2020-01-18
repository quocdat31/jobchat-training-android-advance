package com.example.androidadvance2

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class App : Application(){

    override fun onCreate() {
        super.onCreate()
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var musicService = NotificationChannel(CHANNEL_ID, "test", NotificationManager.IMPORTANCE_DEFAULT)
            var notificationManager: NotificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(musicService)
        }
    }

    companion object {
        val CHANNEL_ID = "musicServiceChannel"
    }

}
