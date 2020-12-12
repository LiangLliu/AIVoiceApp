package com.edwin.lib_voice.manager

import android.content.Context
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

    //---------------------------TTS Start---------------------------
    // 播放
    fun start(text: String) {
        VoiceTTS.start(text)
    }

    // 暂停
    fun pause() {
        VoiceTTS.pause()
    }

    // 继续播放
    fun resume() {
        VoiceTTS.resume()
    }

    // 停止播放
    fun stop() {
        VoiceTTS.stop()
    }

    // 释放资源
    fun release() {
        VoiceTTS.release()
    }

    //---------------------------TTS End-----------------------------
}
