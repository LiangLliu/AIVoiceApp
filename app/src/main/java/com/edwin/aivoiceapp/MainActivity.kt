package com.edwin.aivoiceapp

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

        mList = ArrayList()

        for (i in 1..30) {
            mList.add("第${i}个")
        }


        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter =
            CommonAdapter(mList, object : CommonAdapter.OnBindDataListener<String> {
                override fun onBindViewHolder(
                    model: String,
                    viewHolder: CommonViewHolder,
                    type: Int,
                    position: Int
                ) {
                    viewHolder.setText(R.id.textView, model)
                }

                override fun getLayoutId(type: Int): Int {
                    return R.layout.test_item
                }

            })
    }


}
