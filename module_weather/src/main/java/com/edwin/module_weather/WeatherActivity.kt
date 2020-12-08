package com.edwin.module_weather

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_WEATHER)
class WeatherActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_weather
    }

    override fun getTitleText(): String {
        return getString(com.edwin.lib_base.R.string.app_title_weather)
    }

    override fun isShowBack(): Boolean {
        return false
    }

    override fun initView() {

    }

}