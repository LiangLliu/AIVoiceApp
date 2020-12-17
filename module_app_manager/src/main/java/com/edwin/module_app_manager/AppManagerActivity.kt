package com.edwin.module_app_manager

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.base.adapter.BasePagerAdapter
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_base.helper.func.AppHelper
import com.edwin.lib_base.utils.L
import kotlinx.android.synthetic.main.activity_app_manager.*

/**
 * 应用管理
 */

@Route(path = ARouterHelper.PATH_APP_MANAGER)
class AppManagerActivity : BaseActivity() {


    private val waitApp = 1000

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == waitApp) {
                waitAppHandler()
            }
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_app_manager
    }

    override fun getTitleText(): String {
        return getString(com.edwin.lib_base.R.string.app_title_app_manager)
    }

    override fun isShowBack(): Boolean {
        return true
    }

    override fun initView() {

        ll_loading.visibility = View.VISIBLE

        if (AppHelper.mAllViewList.size > 0) {
            initViewPager()
        } else {
            mHandler.sendEmptyMessageDelayed(waitApp, 1000)
        }

    }


    //等待应用加载完成
    private fun waitAppHandler() {
        L.i("等待App列表刷新...")
        if (AppHelper.mAllViewList.size > 0) {
            initViewPager()
        } else {
            mHandler.sendEmptyMessageDelayed(waitApp, 1000)
        }
    }

    /**
     * 初始化页面
     */
    private fun initViewPager() {
        mViewPager.offscreenPageLimit = AppHelper.getPageSize()
        mViewPager.adapter = BasePagerAdapter(AppHelper.mAllViewList)
        ll_loading.visibility = View.GONE
        ll_content.visibility = View.VISIBLE

        mPointLayoutView.setPointSize(AppHelper.getPageSize())

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                mPointLayoutView.setCheck(position)
            }
        })
    }


}