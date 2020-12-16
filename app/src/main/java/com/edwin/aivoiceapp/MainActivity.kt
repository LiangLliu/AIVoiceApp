package com.edwin.aivoiceapp

import android.content.Intent
import com.edwin.aivoiceapp.service.VoiceService
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_base.utils.L
import com.edwin.lib_network.HttpManager
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response


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


        startService(Intent(this, VoiceService::class.java))

        testWeather()

        ARouterHelper.startActivity(ARouterHelper.PATH_DEVELOPER)

    }

    // https://www.juhe.cn/docs/api/id/73
    private fun testWeather() {

        HttpManager.queryWeather("北京")
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    L.i("请求失败")
                }

                override fun onResponse(
                    call: Call<ResponseBody>, response: Response<ResponseBody>
                ) {
                    L.i("请求成功：" + response.body()?.string())
                }

            })
    }


}
