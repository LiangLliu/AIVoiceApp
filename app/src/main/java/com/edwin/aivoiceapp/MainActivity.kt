package com.edwin.aivoiceapp

import android.content.Intent
import com.edwin.aivoiceapp.service.VoiceService
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.helper.ARouterHelper

import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission


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

        ARouterHelper.startActivity(ARouterHelper.PATH_DEVELOPER)
    }


}
