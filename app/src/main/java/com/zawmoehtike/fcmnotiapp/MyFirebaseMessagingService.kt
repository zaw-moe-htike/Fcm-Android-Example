package com.zawmoehtike.fcmnotiapp

import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.os.Looper



class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCMLogger", "From: ${remoteMessage?.from}")


        // Check if message contains a notification payload.
        remoteMessage?.notification?.let {
            Log.d("FCMLogger", "Message Notification Body: ${it.body}")
            //Message Services handle notification
            val notification = NotificationCompat.Builder(this)
                .setContentTitle(remoteMessage.from)
                .setContentText(it.body)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build()
            val manager = NotificationManagerCompat.from(applicationContext)
            manager.notify(/*notification id*/0, notification)

            val handler = Handler(Looper.getMainLooper())
            handler.post(Runnable {
                Toast.makeText(
                    applicationContext,
                    it.body.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
    }

    override fun onNewToken(token: String) {
        //handle token
    }
}