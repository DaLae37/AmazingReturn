package com.dalae37.amazingreturn

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat


class ReturnActivity : android.app.Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()

        val id : String = getString(R.string.notification_id)
        val title :String = getString(R.string.app_name)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var channel = notificationManager.getNotificationChannel(id)
        if (channel == null) {
            channel = NotificationChannel(id, title, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, id).build()
        startForeground(1, notification)
    }
}