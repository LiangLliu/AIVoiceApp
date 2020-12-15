package com.edwin.aivoiceapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.edwin.lib_base.helper.NotificationHelper
import com.edwin.lib_base.utils.L
import com.edwin.lib_voice.engine.VoiceEngineAnalyze
import com.edwin.lib_voice.impl.OnAsrResultListener
import com.edwin.lib_voice.impl.OnNluResultListener
import com.edwin.lib_voice.manager.VoiceManager
import com.edwin.lib_voice.tts.VoiceTTS
import com.edwin.lib_voice.words.WordTools
import org.json.JSONObject

class VoiceService : Service(), OnNluResultListener {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        L.i("语音服务启动")
        initCoreVoiceService()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindNotification()
        return START_STICKY_COMPATIBILITY
    }

    //    绑定通知栏
    private fun bindNotification() {
        startForeground(1000, NotificationHelper.bindVoiceService("正在运行"))
    }

    private fun initCoreVoiceService() {
        VoiceManager.initManager(this, object : OnAsrResultListener {
            override fun wakeUpReady() {
                L.i("唤醒准备就绪")
                VoiceManager.ttsStart("唤醒引擎准备就绪")
            }

            override fun asrStartSpeak() {
                L.i("开始播放")
            }

            override fun asrStopSpeak() {
                L.i("结束说话")
            }

            override fun wakeUpSuccess(result: JSONObject) {
                L.i("唤醒成功：$result")
                // 当唤醒词是小爱，才开启
                val errorCode = result.optInt("errorCode")
                if (0 == errorCode) {
                    // 唤醒成功
                    val wakeUpWord = result.optString("word")

                    if (wakeUpWord == "小爱同学" || wakeUpWord == "小袁同学" || wakeUpWord == "小袁小袁") {
                        // 应答
                        VoiceManager.ttsStart(
                            WordTools.wakeUpWords(),
                            object : VoiceTTS.OnTTSResultListener {
                                override fun ttsEnd() {
                                    // 开启识别
                                    VoiceManager.startAsr()
                                }
                            })
                    }
                }
            }

            override fun asrResult(result: JSONObject) {
                L.i("=======================result=========================")
                L.i("result： $result")
            }

            override fun nluResult(nlu: JSONObject) {
                L.i("=======================nlu=========================")
                L.i("nlu： $nlu")
                VoiceEngineAnalyze.analyzeNlu(nlu, this@VoiceService)
            }

            override fun voiceError(error: String) {
                L.i("发生错误：$error")
            }
        })
    }

    // 查询天气
    override fun queryWeather() {

    }


}