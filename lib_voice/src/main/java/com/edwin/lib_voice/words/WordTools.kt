package com.edwin.lib_voice.words

import android.content.Context
import com.edwin.lib_voice.R
import kotlin.random.Random

/**
 * 词条工具
 */
object WordTools {

    // 唤醒词条
    private lateinit var wakeUpArray: Array<String>

    // 无法应答

    private lateinit var noAnswerArray: Array<String>

    // 暂不支持功能

    private lateinit var noSupportArray: Array<String>


    // 初始化工具
    fun initTools(context: Context) {
        context.apply {
            wakeUpArray = resources.getStringArray(R.array.WakeUpListArray)
            noAnswerArray = resources.getStringArray(R.array.NoAnswerArray)
            noSupportArray = resources.getStringArray(R.array.NoSupportArray)
        }
    }

    // 唤醒
    fun wakeUpWords(): String {
        return randomArray(wakeUpArray)
    }

    // 无法应答
    fun noAnswerWords(): String {
        return randomArray(noAnswerArray)
    }

    fun noSupportWords(): String {
        return randomArray(noSupportArray)
    }


    private fun randomArray(array: Array<String>): String {
        return array[Random.nextInt(array.size)]
    }

}