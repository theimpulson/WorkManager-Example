package dev.theimpulson.workmanagerexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class MainActivityWorkManager(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    private val CHANNEL_ID = "MainActivity"

    val notification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_android_black_24dp)
        .setContentTitle("Hello World")
        .setContentText("This is your test notification")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .build()

    override fun doWork(): Result {
        createNotificationChannel()

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(1, notification)
        }

        return Result.success()
    }

    fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Test Notification",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "This is your MainActivity's test channel"
        }
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}