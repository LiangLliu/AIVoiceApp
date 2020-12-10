package com.edwin.lib_base.helper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

/**
 * 通知栏的帮助类
 */
object NotificationHelper {

    private lateinit var mContext: Context

    private lateinit var mNotificationManager: NotificationManager

    private const val CHANNEL_ID = "ai_voice_service"
    private const val CHANNEL_NAME = "语音服务"

    //    初始化帮助类
    fun initHelper(mContext: Context) {

        this.mContext = mContext
        mNotificationManager =
            mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //  创建渠道
        setBindVoiceChannel()

    }

    //    设置绑定服务的渠道
    private fun setBindVoiceChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)

            //  呼吸灯，震动，角标取消
            notificationChannel.enableLights(false)
            notificationChannel.enableVibration(false)
            notificationChannel.setShowBadge(false)

            mNotificationManager.createNotificationChannel(notificationChannel)
        }

    }

    fun bindVoiceService(contentText: String): Notification {


        val notificationCompat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(mContext, CHANNEL_ID)
        } else {
            NotificationCompat.Builder(mContext)
        }


        //  设置标题
        notificationCompat.setContentTitle(CHANNEL_NAME)

        // 设置描述
        notificationCompat.setContentText(contentText)

        notificationCompat.setWhen(System.currentTimeMillis())

        // 禁止滑动
        notificationCompat.setAutoCancel(false)

        return notificationCompat.build()

    }

}