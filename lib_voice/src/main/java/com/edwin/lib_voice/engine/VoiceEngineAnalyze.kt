package com.edwin.lib_voice.engine

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import com.edwin.lib_voice.impl.OnNluResultListener
import com.imooc.lib_voice.words.NluWords
import org.json.JSONArray
import org.json.JSONObject

/**
 * 语音隐情分析
 */
object VoiceEngineAnalyze {

    private var TAG: String = VoiceEngineAnalyze::class.java.simpleName

    private lateinit var mOnNluResultListener: OnNluResultListener

    /**
     * 分析结果
     */
    fun analyzeNlu(nlu: JSONObject, mOnNluResultListener: OnNluResultListener) {


        this.mOnNluResultListener = mOnNluResultListener

        // 用户说的话
        val rawText = nlu.optString("raw_text")
        Log.i(TAG, "rawText: $rawText")


        // 用户说的话
        val results = nlu.optJSONArray("results") ?: return

        val nluResultLength = results.length()

        when {
            nluResultLength <= 0 -> return

            // 单条
            nluResultLength == 1 -> analyzeNluSingle(results[0] as JSONObject)

            else -> {
                // 多条
            }
        }
        Log.i(TAG, "results: $results")

    }

    /**
     * 分析单条结果
     */
    private fun analyzeNluSingle(result: JSONObject) {
        val domain = result.optString("domain")
        val intent = result.optString("intent")
        val slots = result.optJSONObject("slots")


        slots?.let {
            when (domain) {
                NluWords.NLU_APP -> {
                    when (intent) {
                        NluWords.INTENT_OPEN_APP,
                        NluWords.INTENT_UNINSTALL_APP,
                        NluWords.INTENT_UPDATE_APP,
                        NluWords.INTENT_DOWNLOAD_APP,
                        NluWords.INTENT_SEARCH_APP,
                        NluWords.INTENT_RECOMMEND_APP -> {
                            nluOfApp(it, intent)
                        }
                        else -> {
                            mOnNluResultListener.nluError()
                        }
                    }
                }

                NluWords.NLU_WEATHER -> {
                    val userLoc = slots.optJSONArray("user_loc")
                    userLoc?.let { loc ->
                        if (loc.length() > 0) {
                            val locObject = loc[0] as JSONObject
                            val word = locObject.optString("word")
                            if (intent == NluWords.INTENT_USER_WEATHER) {
                                mOnNluResultListener.queryWeather(word)
                            } else {
                                mOnNluResultListener.queryWeatherInfo(word)
                            }
                        }
                    }
                }


                else -> mOnNluResultListener.nluError()
            }
        }

    }

    private fun nluOfApp(it: JSONObject, intent: String) {
        //得到打开App的名称
        val userAppName = it.optJSONArray("user_app_name")
        userAppName?.let { appName ->
            if (appName.length() > 0) {
                val obj = appName[0] as JSONObject
                val word = obj.optString("word")
                when (intent) {
                    NluWords.INTENT_OPEN_APP -> mOnNluResultListener.openApp(
                        word
                    )
                    NluWords.INTENT_UNINSTALL_APP -> mOnNluResultListener.unInstallApp(
                        word
                    )
                    else -> mOnNluResultListener.otherApp(word)
                }
            } else {
                mOnNluResultListener.nluError()
            }
        }
    }
}