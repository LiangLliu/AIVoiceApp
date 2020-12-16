package com.edwin.lib_network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 拦截器
 */
class HttpInterceptor : Interceptor {

    private val TAG = "HTTP"

    override fun intercept(chain: Interceptor.Chain): Response {
        // 请求参数
        val request = chain.request()

        val response = chain.proceed(request)

        Log.i(TAG, "==============REQUEST===============")

        if (request.method() == "GET") {
            Log.i(TAG, request.url().toString())
        }
        Log.i(TAG, "==============RESPONSE==============")
        response.body()?.let {
            Log.i(TAG, it.string())
        }

        return response
    }
}