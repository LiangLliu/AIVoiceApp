package com.edwin.lib_network

import com.edwin.lib_network.http.HttpKey
import com.edwin.lib_network.http.HttpUrl
import com.edwin.lib_network.impl.HttpImplService
import com.edwin.lib_network.interceptor.HttpInterceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 对外的网络管理
 */
object HttpManager {

    // 创建客户端
    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpInterceptor())
            .build()
    }

    // 天气对象
    private val retrofitWeather by lazy {
        Retrofit.Builder()
            .client(getClient())
            .baseUrl(HttpUrl.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // 天气接口对象
    private val apiWeather by lazy {
        retrofitWeather.create(HttpImplService::class.java)
    }

    // 获取天气
    fun queryWeather(city: String): Call<ResponseBody> {
        return apiWeather.getWeather(city, HttpKey.WEATHER_KEY)
    }

}