package com.edwin.lib_voice.tts

import android.content.Context
import android.util.Log
import com.baidu.tts.client.SpeechError
import com.baidu.tts.client.SpeechSynthesizer
import com.baidu.tts.client.SpeechSynthesizerListener
import com.baidu.tts.client.TtsMode
import com.edwin.lib_voice.manager.VoiceManager

/**
 * 百度AI语音  —— TTS
 */
object VoiceTTS : SpeechSynthesizerListener {

    private var TAG = VoiceTTS::class.java.simpleName


    // TTS对象
    private lateinit var mSpeechSynthesizer: SpeechSynthesizer


    // 初始化TTS
    fun initTTS(mContext: Context) {

        //初始化对象
        mSpeechSynthesizer = SpeechSynthesizer.getInstance()
        //设置上下文
        mSpeechSynthesizer.setContext(mContext)
        //设置Key
        mSpeechSynthesizer.setAppId(VoiceManager.VOICE_APP_ID)
        mSpeechSynthesizer.setApiKey(VoiceManager.VOICE_APP_KEY, VoiceManager.VOICE_APP_SECRET)
        //设置监听
        mSpeechSynthesizer.setSpeechSynthesizerListener(this)

        // 其他参数
        // 发声人
        // 语速
        // 音量

        // 初始化
        mSpeechSynthesizer.initTts(TtsMode.ONLINE)

    }

    override fun onSynthesizeStart(p0: String?) {
        Log.i(TAG, "合成开始")
    }

    override fun onSynthesizeDataArrived(p0: String?, p1: ByteArray?, p2: Int, p3: Int) {
        Log.i(TAG, "合成过程中的数据回调接口")
    }


    override fun onSynthesizeFinish(p0: String?) {
        Log.i(TAG, "合成结束")
    }

    override fun onSpeechStart(p0: String?) {
        Log.i(TAG, "播放开始")
    }


    override fun onSpeechProgressChanged(p0: String?, p1: Int) {
        Log.i(TAG, "播放过程中的回调")
    }

    override fun onSpeechFinish(p0: String?) {
        Log.i(TAG, "播放结束")
    }

    override fun onError(string: String?, error: SpeechError?) {
        Log.e(TAG, "TTS 错误：$string, $error")
    }


    // --------------------------------------------
    // 播放
    fun start(text: String) {
        mSpeechSynthesizer.speak(text)
    }

    // 暂停
    fun pause() {
        mSpeechSynthesizer.pause()
    }

    // 继续播放
    fun resume() {
        mSpeechSynthesizer.resume()
    }

    // 停止播放
    fun stop() {
        mSpeechSynthesizer.stop()
    }

    // 释放资源
    fun release() {
        mSpeechSynthesizer.release()
    }

}