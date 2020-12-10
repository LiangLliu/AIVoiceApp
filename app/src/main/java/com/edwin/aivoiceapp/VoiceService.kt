package com.edwin.aivoiceapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.edwin.lib_base.helper.NotificationHelper

class VoiceService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindNotification()
        return START_STICKY_COMPATIBILITY
    }

    //    绑定通知栏
    private fun bindNotification() {
        startForeground(1000, NotificationHelper.bindVoiceService("正在运行"))
    }


}