package com.ch2ps385.nutrimate.presentation.screen.user.reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ch2ps385.nutrimate.R
import com.ch2ps385.nutrimate.common.NOTIFICATION_CHANNEL_ID
//
//class ReminderNotificationWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
//
//    companion object {
//        private const val CHANNEL_NAME = "nutrimateapp"
//        private const val TAG = "ReminderNotificationWorker"
//        private const val NOTIFICATION_ID = 1
//    }
//    override fun doWork(): Result {
//
//        Log.d("ReminderNotificationWorker", "Work execution")
//
//        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notifBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            .setSmallIcon(R.drawable.ic_notifications)
//            .setContentTitle("Drink Reminder")
//            .setContentText("Lets go to drink some water!")
////            .setContentIntent(getPendingIntent(habitId))
//            .setAutoCancel(true)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                NOTIFICATION_CHANNEL_ID,
//                CHANNEL_NAME,
//                NotificationManager.IMPORTANCE_HIGH
//            )
//            notifBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        Log.d(TAG,"Notification Launched!")
//        val notification = notifBuilder.build()
//        notificationManager.notify(NOTIFICATION_ID, notification)
//
//        return Result.success()
//    }
//
////    private fun getPendingIntent(): PendingIntent? {
////        val intent = Intent(applicationContext, NutriMateApp::class.java).apply {
////            action = "OPEN_REMINDER_SCREEN"
////        }
////
////        return TaskStackBuilder.create(applicationContext).run {
////            addNextIntentWithParentStack(intent)
////            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
////                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
////            } else {
////                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
////            }
////        }
////    }
//}
class ReminderNotificationWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    companion object {
        private const val CHANNEL_NAME = "nutrimateapp"
        private const val TAG = "ReminderNotificationWorker"
        private const val NOTIFICATION_ID = 1
    }

    override fun doWork(): Result {
        if (hasNotificationPermissions()) {
            createAndShowNotification()
            return Result.success()
        } else {
            Log.e(TAG, "Notification permissions not granted")
            return Result.failure()
        }
    }

    private fun createAndShowNotification() {
        Log.d("ReminderNotificationWorker", "Work execution")

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Adjusted importance to high
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Drink Reminder")
            .setContentText("Let's go drink some water!")
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notifBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        Log.d(TAG, "Notification Launched!")
        val notification = notifBuilder.build()
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun hasNotificationPermissions(): Boolean {
        val notificationPermissions = arrayOf(
            android.Manifest.permission.VIBRATE,
            android.Manifest.permission.WAKE_LOCK
            // Add other necessary permissions
        )

        return notificationPermissions.all {
            ContextCompat.checkSelfPermission(applicationContext, it) == PackageManager.PERMISSION_GRANTED
        }
    }
}