package com.fly.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    private lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(packageName, "Demo", NotificationManager.IMPORTANCE_HIGH)
            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun normal(view: View) {
        val intent = Intent(this, SecondMainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, packageName)
            .setContentTitle("一般通知")
            .setContentText("收到一條消息")
            .setSmallIcon(R.drawable.logo)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon))
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify((0..10000).random(), notification)
    }

    fun progress(view: View) {
        val notification = NotificationCompat.Builder(this, packageName)
            .setContentTitle("圖片下載")
            .setContentText("下載中")
            .setSmallIcon(R.drawable.logo)
            .setWhen(System.currentTimeMillis())
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.icon))
            )
            .setProgress(100, 0, false)

        thread {
            for (i in 0..100 step 5) {
                notification.setProgress(100, i, false)
                notificationManager.notify(0, notification.build())
                Thread.sleep(250)
            }
            notification.setProgress(0, 0, false)
                .setContentText("下載完成")
            notificationManager.notify(0, notification.build())
            Thread.sleep(1000)
            notificationManager.cancel(0)
        }

        notificationManager.notify(0, notification.build())
    }

    fun bigPicture(view: View) {
        val intent = Intent(this, SecondMainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, packageName)
            .setContentTitle("大圖通知")
            .setContentText("收到一條消息")
            .setSmallIcon(R.drawable.logo)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.icon))
            )
            .build()

        notificationManager.notify((0..10000).random(), notification)
    }


    fun moreLine(view: View) {
        val intent = Intent(this, SecondMainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val messageList = ArrayList<String>();
        messageList.add("第一條訊息");
        messageList.add("第二條訊息");
        messageList.add("第三條訊息");
        messageList.add("第四條訊息");
        messageList.add("第五條訊息");

        val inboxStyle = NotificationCompat.InboxStyle();
        messageList.forEach {
            inboxStyle.addLine(it);
        }

        val notification = NotificationCompat.Builder(this, packageName)
            .setContentTitle("多行通知")
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.drawable.logo)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setStyle(inboxStyle)
            .build()

        notificationManager.notify((0..10000).random(), notification)
    }
}