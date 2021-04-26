package sobaya.example.flowsample.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationManagerCompat

private const val notificationId = "notification_id"

fun createNotificationChannel(context: Context) {

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        return
    }

    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val name = "通知チャンネル"
    val description = "通知のテストです"

    if (manager.getNotificationChannel(notificationId) == null) {
        val channel = NotificationChannel(notificationId, name, NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = description
        manager.createNotificationChannel(channel)
    }
}

fun makeNotificationSettingIntent(context: Context): Intent {
    val intent = Intent()
    intent.action = android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS
    intent.putExtra("app_package", context.packageName)
    intent.putExtra("app_uid", context.applicationInfo.uid)
    intent.putExtra("android.provider.extra.APP_PACKAGE", context.packageName)

    return intent
}

fun isEnableNotificationSetting(context: Context): Boolean {
    val manager: NotificationManagerCompat = NotificationManagerCompat.from(context)
    val channel = manager.getNotificationChannel(notificationId)
    val isNotice: Boolean = manager.areNotificationsEnabled()

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelNotice = channel?.importance != NotificationManager.IMPORTANCE_NONE && isNotice
        channelNotice
    } else {
        isNotice
    }
}
