package com.edwin.module_voice_setting

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_VOICE_SETTING)
class VoiceSettingActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_voice_setting
    }

    override fun getTitleText(): String {
        return getString(com.edwin.lib_base.R.string.app_title_voice_setting)
    }

    override fun isShowBack(): Boolean {
        return false
    }

    override fun initView() {

    }
}