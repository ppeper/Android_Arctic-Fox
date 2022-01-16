package com.kyonggi.notifydemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kyonggi.notifydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationManager =
            getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            "com.kyonggi.notifydemo.news",
            "NotifyDemo News",
            "Example News Channel"
        )

    }

    private fun createNotificationChannel(
        id: String, name: String,
        description: String
    ) {
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
            longArrayOf(100,200,300,400,500,400,300,200,400)

        notificationManager?.createNotificationChannel(channel)
    }

    fun sendNotification(view: View) {

        val notificationID = 101
        val resultIntent = Intent(this, ResultActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelID = "com.kyonggi.notifydemo.news"

        // 알림 아래 Action 추가
        val icon: Icon = Icon.createWithResource(this, android.R.drawable.ic_dialog_info)

        val action: Notification.Action =
            Notification.Action.Builder(icon, "Open", pendingIntent).build()
        //--------------------//

        val notification = Notification.Builder(
            this,
            channelID
        ).setContentTitle("Example Notification")
            .setContentText("This is an example notification.")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setChannelId(channelID)
            .setContentIntent(pendingIntent)
            .setActions(action)
            .setNumber(10)
            .build()

        notificationManager?.notify(notificationID, notification)

        // 요약 알림
        val GROUP_KEY_NOTIFY = "group_key_notify"

        val builderSummary: Notification.Builder = Notification.Builder(this, channelID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("A Bundle Example")
            .setContentText("You have a 3 new messages")
            .setGroup(GROUP_KEY_NOTIFY)
            .setGroupSummary(true)

        val builder1: Notification.Builder = Notification.Builder(this, channelID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("New Message")
            .setContentText("You have a new message from Kassidy")
            .setGroup(GROUP_KEY_NOTIFY)

        val builder2: Notification.Builder = Notification.Builder(this, channelID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("New Message")
            .setContentText("You have a new message from Caitlyn")
            .setGroup(GROUP_KEY_NOTIFY)

        val builder3: Notification.Builder = Notification.Builder(this, channelID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("New Message")
            .setContentText("You have a new message from Jason")
            .setGroup(GROUP_KEY_NOTIFY)

        val notificationId0 = 100
        val notificationId1= 101
        val notificationId2 = 102
        val notificationId3 = 103

        notificationManager?.notify(notificationId1, builder1.build())
        notificationManager?.notify(notificationId2, builder2.build())
        notificationManager?.notify(notificationId3, builder3.build())
        notificationManager?.notify(notificationId0, builderSummary.build())
        // -------------- //
    }
}