package com.edwin.module_constellation

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.helper.ARouterHelper

@Route(path = ARouterHelper.PATH_CONSTELLATION)
class ConstellationActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)
    }


}