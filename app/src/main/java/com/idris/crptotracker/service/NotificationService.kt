package com.idris.crptotracker.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import android.app.Notification
import com.idris.crptotracker.R
import com.idris.crptotracker.ui.activities.MainActivity

class NotificationService : Service() {
    private val LOG_TAG = this.javaClass.simpleName
    private val NOTIFICATION_CHANNEL_ID = LOG_TAG
    var notification: Notification? = null

    override fun onCreate() {
        Log.d(LOG_TAG, "onCreate()")
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_LOW
                )
            )
        }
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy()")
        super.onDestroy()
    }

    @Synchronized
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand()")
        if (notification == null) {
            notification = getNotification()
        }
        startForeground(NOTIFICATION_ID, notification)
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(LOG_TAG, "onBind()")
        return null
    }

    @JvmName("getNotification1")
    private fun getNotification(): Notification {
        val context: Context = this
        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_MUTABLE)
        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.new_bitcoin_rate))
            .setContentIntent(pendingIntent).build()
    }

    companion object {
        private const val NOTIFICATION_ID = 123
    }
}