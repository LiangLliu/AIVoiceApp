package com.edwin.module_joke

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_JOKE)
class JokeActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.joke_activity
    }

    override fun getTitleText(): String {
        return getString(com.edwin.lib_base.R.string.app_title_joke)
    }

    override fun isShowBack(): Boolean {
        return false
    }

    override fun initView() {

    }


}