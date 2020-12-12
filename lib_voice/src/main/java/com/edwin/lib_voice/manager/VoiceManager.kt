package com.edwin.lib_voice.manager

import android.content.Context
import com.baidu.tts.client.SpeechSynthesizer
import com.edwin.lib_voice.tts.VoiceTTS

/**
 * 语音管理类
 */
object VoiceManager {

// me
//    const val VOICE_APP_ID = "23133129"
//    const val VOICE_APP_KEY = "pzSuYvo7lonrlBUseHqGrAyj"
//    const val VOICE_APP_SECRET = "mmdBcaIQUoiDEgPlIBgBflWpdzQfsCcl"

    //语音Key
    const val VOICE_APP_ID = "19389428"
    const val VOICE_APP_KEY = "3HzYMWVyEuOuWWrExaCUEEKg"
    const val VOICE_APP_SECRET = "q1zVsCEoD4lkchSanyESH7PcLN2GGOSF"


    fun initManager(mContent: Context) {
        VoiceTTS.initTTS(mContent)
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
        VoiceTTS.start(text)
    }

    // 播放并监听结果
    fun ttsStart(text: String, mOnTTSResultListener: VoiceTTS.OnTTSResultListener) {
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
}
