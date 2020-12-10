package com.edwin.aivoiceapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.base.adapter.CommonAdapter
import com.edwin.lib_base.base.adapter.CommonViewHolder
import com.edwin.lib_base.event.EventManager
import com.edwin.lib_base.event.MessageEvent
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_base.utils.L
import com.edwin.lib_base.utils.SpUtils
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


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
