package com.edwin.lib_voice.manager

import android.content.Context
import android.util.Log
import com.baidu.speech.EventListener
import com.baidu.speech.asr.SpeechConstant
import com.edwin.lib_voice.tts.VoiceTTS
import com.edwin.lib_voice.wakeup.VoiceWakeUp


/**
 * 语音管理类
 */
object VoiceManager : EventListener {

    // me
    const val VOICE_APP_ID = "23133129"
    const val VOICE_APP_KEY = "pzSuYvo7lonrlBUseHqGrAyj"
    const val VOICE_APP_SECRET = "mmdBcaIQUoiDEgPlIBgBflWpdzQfsCcl"

//    //语音Key
//    const val VOICE_APP_ID = "19389428"
//    const val VOICE_APP_KEY = "3HzYMWVyEuOuWWrExaCUEEKg"
//    const val VOICE_APP_SECRET = "q1zVsCEoD4lkchSanyESH7PcLN2GGOSF"


    private var TAG = VoiceManager::class.java.simpleName

    fun initManager(mContent: Context) {
        VoiceTTS.initTTS(mContent)
        VoiceWakeUp.initWakeup(mContent, this)
    }


    // 设置发音人
    fun setPeople(people: String) {
        VoiceTTS.setPeople(people)
    }

    // 设置语速
    fun setVoiceSpeed(voiceSpeed: String) {
        VoiceTTS.setVoiceSpeed(voiceSpeed)
    }

    // 设置音量
    fun setVoiceVolume(voiceVolume: String) {
        VoiceTTS.setVoiceVolume(voiceVolume)
    }

    //---------------------------TTS Start---------------------------
    // 播放
    fun ttsStart(text: String) {
        Log.d(TAG, "开始TTS：$text")
        VoiceTTS.start(text)
    }

    // 播放并监听结果
    fun ttsStart(text: String, mOnTTSResultListener: VoiceTTS.OnTTSResultListener) {
        Log.d(TAG, "开始TTS：$text")
        VoiceTTS.start(text, mOnTTSResultListener)
    }

    // 暂停
    fun ttsPause() {
        VoiceTTS.pause()
    }

    // 继续播放
    fun ttsResume() {
        VoiceTTS.resume()
    }

    // 停止播放
    fun ttsStop() {
        VoiceTTS.stop()
    }

    // 释放资源
    fun ttsRelease() {
        VoiceTTS.release()
    }


    //---------------------------TTS End-----------------------------



    //---------------------------WakeUp Start-----------------------------

    // 启动唤醒
    fun startWakeUp() {
        Log.d(TAG, "启动唤醒")
        VoiceWakeUp.startWakeUp()
    }

    // 停止唤醒
    fun stopWakeUp() {
        VoiceWakeUp.stopWakeUp()
    }

    //---------------------------WakeUp End-----------------------------

    override fun onEvent(
        name: String?, params: String?, data: ByteArray?, offset: Int, length: Int
    ) {
        Log.d(TAG, String.format("event: name=%s, params=%s", name, params));

        name?.let {
            when (it) {
               SpeechConstant.CALLBACK_EVENT_WAKEUP_SUCCESS -> ttsStart("我在，有什么可以帮到你")
            }
        }
    }

}
