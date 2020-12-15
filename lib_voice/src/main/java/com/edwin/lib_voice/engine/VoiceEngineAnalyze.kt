package com.edwin.lib_voice.engine

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

        when (domain) {
            NluWords.NLU_WEATHER -> {

            }
        }
    }


}