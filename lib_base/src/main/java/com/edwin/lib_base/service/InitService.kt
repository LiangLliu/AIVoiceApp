package com.edwin.lib_base.service

import android.app.IntentService
import android.app.NotificationManager
import android.content.Intent
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_base.helper.NotificationHelper
import com.edwin.lib_base.helper.SoundPoolHelper
import com.edwin.lib_base.helper.func.AppHelper
import com.edwin.lib_base.utils.L
import com.edwin.lib_base.utils.SpUtils
import com.edwin.lib_voice.manager.VoiceManager
import com.edwin.lib_voice.words.WordTools

/**
 * 初始化服务
 * 一般做短任务服务
 */
// todo 该服务已过期，需要替换
class InitService : IntentService(InitService::class.simpleName) {

    override fun onCreate() {
        super.onCreate()
        L.i("init sevice 启动")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onHandleIntent(intent: Intent?) {
        L.i("初始化操作")


        SpUtils.initUtils(this)
        WordTools.initTools(this)
        SoundPoolHelper.init(this)

        AppHelper.initHelper(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        L.i("初始化完成")
    }
}