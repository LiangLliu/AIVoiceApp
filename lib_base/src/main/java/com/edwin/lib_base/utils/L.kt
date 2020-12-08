package com.edwin.lib_base.utils

import android.util.Log
import com.edwin.lib_base.BuildConfig

/**
 * log 日志
 */
object L {
    private const val TAG: String = "AIVoiceAPP"

    fun i(text: String?) {
        if (BuildConfig.DEBUG) {
            text?.let {
                Log.i(TAG, it)
            }
        }
    }


    fun e(text: String?) {
        if (BuildConfig.DEBUG) {
            text?.let {
                Log.e(TAG, it)
            }
        }
    }
}