package com.edwin.aivoiceapp

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 天气测试类
 */
interface TestWeatherService {

    @GET("、simpleWeather/query")
    fun getWeather(@Query("city") city: String, @Query("key") key: String): Call<ResponseBody>

}