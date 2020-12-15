package com.edwin.aivoiceapp

import android.content.Intent
import com.edwin.aivoiceapp.service.VoiceService
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_base.utils.L
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : BaseActivity() {


    private lateinit var mList: ArrayList<String>


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleText(): String {
        return getString(R.string.app_name)
    }

    override fun isShowBack(): Boolean {
        return false
    }


    override fun initView() {

        testWeather()
        startService(Intent(this, VoiceService::class.java))

        ARouterHelper.startActivity(ARouterHelper.PATH_DEVELOPER)


    }


    // https://www.juhe.cn/docs/api/id/73
    private fun testWeather() {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://apis.juhe.cn/")
            .build()

        val service = retrofit.create(TestWeatherService::class.java)
        service.getWeather("北京", "133123").equals(object : Callback<RequestBody> {
            override fun onFailure(call: Call<RequestBody>, t: Throwable) {
                L.i("请求失败")
            }

            override fun onResponse(call: Call<RequestBody>, response: Response<RequestBody>) {

                L.i("请求成功：" + response.body().toString())
            }

        })
    }


}
