package com.edwin.aivoiceapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.event.EventManager
import com.edwin.lib_base.event.MessageEvent
import com.edwin.lib_base.helper.ARouterHelper
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity() {
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
        ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
    }


}
