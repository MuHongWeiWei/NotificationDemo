# Android Notification 通知訊息
<ol>
  <li><a href="https://badgameshow.com/fly/android-notification通知/fly/android/#a">Android8.0以上一定要設置Channel渠道</a></li>
  <li><a href="https://badgameshow.com/fly/android-notification通知/fly/android/#b">一般通知</a></li>
  <li><a href="https://badgameshow.com/fly/android-notification通知/fly/android/#c">進度條通知</a></li>
  <li><a href="https://badgameshow.com/fly/android-notification通知/fly/android/#d">大圖片通知</a></li>
  <li><a href="https://badgameshow.com/fly/android-notification通知/fly/android/#e">多行通知</a></li>
  <li><a href="https://badgameshow.com/fly/android-notification通知/fly/android/#f">Github</a></li>
</ol>

---

<a id="a"></a>
### 1.Android8.0以上一定要設置Channel渠道
```Kotlin
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    val channel = NotificationChannel(packageName, "Demo", NotificationManager.IMPORTANCE_HIGH)
    notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}
```

<a id="b"></a>
### 2.一般通知
```Kotlin
val intent = Intent(this, SecondMainActivity::class.java)
val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

val notification = NotificationCompat.Builder(this, packageName)
    .setContentTitle("通知")
    .setContentText("收到一條消息")
    .setSmallIcon(R.drawable.logo)
    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.icon))
    .setWhen(System.currentTimeMillis())
    .setContentIntent(pendingIntent)
    .setAutoCancel(true)
    .build()

notificationManager.notify((0..10000).random(), notification)
```

<img src="https://badgameshow.com/fly/wp-content/uploads/2021/03/20210315_163953.gif" width="30%">


<a id="c"></a>
### 3.進度條通知
```Kotlin
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
```
<img src="https://badgameshow.com/fly/wp-content/uploads/2021/03/20210315_164041.gif" width="30%">

<a id="d"></a>
### 4.大圖片通知
```Kotlin
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
```

<img src="https://badgameshow.com/fly/wp-content/uploads/2021/03/20210315_164131.gif" width="30%">

<a id="e"></a>
### 5.多行通知
```Kotlin
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
```

<img src="https://badgameshow.com/fly/wp-content/uploads/2021/03/20210315_164357.gif" width="30%">

<a id="f"></a>
### 6.Github
[Github](https://github.com/MuHongWeiWei/NotificationDemo)
