package com.edwin.module_voice_setting

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_VOICE_SETTING)
class VoiceSettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_setting)
    }
}