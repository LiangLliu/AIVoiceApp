package com.edwin.lib_voice.wakeup


import android.content.Context
import android.util.Log
import com.baidu.speech.EventListener
import com.baidu.speech.EventManager
import com.baidu.speech.EventManagerFactory
import com.baidu.speech.asr.SpeechConstant
import org.json.JSONObject


/**
 * 语音唤醒
 */
object VoiceWakeUp {


    private lateinit var wakeUpJson: String

    // 唤醒对象
    private lateinit var eventManger: EventManager


    fun initWakeup(context: Context, listener: EventListener) {

        val map = HashMap<Any, Any>()

        // 本地文件路径
        map[SpeechConstant.WP_WORDS_FILE] = "assets:///WakeUp.bin"

        // 是否获取音量
        map[SpeechConstant.ACCEPT_AUDIO_VOLUME] = false

        // 转成json
        wakeUpJson = JSONObject(map as Map<Any, Any>).toString()

        // 设置监听器
        eventManger = EventManagerFactory.create(context, "wp")
        eventManger.registerListener(listener)

        startWakeUp()
    }

    // 启动唤醒
    fun startWakeUp() {
        Log.i("VoiceManager", wakeUpJson)
        eventManger.send(SpeechConstant.WAKEUP_START, wakeUpJson, null, 0, 0)
    }

    // 停止唤醒
    fun stopWakeUp() {
        eventManger.send(SpeechConstant.WAKEUP_STOP, null, null, 0, 0)
    }

    // 销毁
    fun releaseWakeUp(listener: EventListener) {
        stopWakeUp();
        eventManger.unregisterListener(listener);

    }


}