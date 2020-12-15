package com.edwin.lib_base.base

import android.app.Application
import android.content.Intent
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_base.helper.NotificationHelper
import com.edwin.lib_base.service.InitService
import com.edwin.lib_base.utils.SpUtils
import com.edwin.lib_voice.manager.VoiceManager

/**
 * 基类APP
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ARouterHelper.initHelper(this)
        // 绑定服务，免杀
        NotificationHelper.initHelper(this)

        startService(Intent(this, InitService::class.java))
    }
}