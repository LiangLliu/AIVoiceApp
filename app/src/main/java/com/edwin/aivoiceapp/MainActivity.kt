package com.edwin.aivoiceapp

import android.Manifest
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.edwin.aivoiceapp.data.MainListData
import com.edwin.aivoiceapp.service.VoiceService
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.base.adapter.BasePagerAdapter
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_base.helper.func.AppHelper
import com.yanzhenjie.permission.Action

import com.zhy.magicviewpager.transformer.ScaleInTransformer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private val mList = ArrayList<MainListData>()

    private val mListView = ArrayList<View>()


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

        // 动态权限
        if (checkPermission(Manifest.permission.RECORD_AUDIO)) {
            linkService()
        } else {
            requestPermission(arrayOf(Manifest.permission.RECORD_AUDIO),
                Action<List<String>> { linkService() })
        }

        //窗口权限
        if (!checkWindowPermission()) {
            requestWindowPermission(packageName)
        }

        initPagerData()
        initPagerView()

    }

    /**
     * 初始化View
     */
    private fun initPagerView() {

        mViewPager.pageMargin = 20
        mViewPager.offscreenPageLimit = mList.size
        mViewPager.adapter = BasePagerAdapter(mListView)

    }

    /**
     * 初始化数据
     */
    private fun initPagerData() {
        val title = resources.getStringArray(R.array.MainTitleArray)
        val color = resources.getIntArray(R.array.MainColorArray)
        val icon = resources.obtainTypedArray(R.array.MainIconArray)
        mViewPager.setPageTransformer(true, ScaleInTransformer())

        for ((index, value) in title.withIndex()) {
            mList.add(MainListData(value, icon.getResourceId(index, 0), color[index]))
        }


        val windowHeight = windowManager.defaultDisplay.height

        mList.forEach {
            val view = View.inflate(this, R.layout.layout_main_list, null)

            val mCvMainView = view.findViewById<CardView>(R.id.mCvMainView)
            val mIvMainIcon = view.findViewById<ImageView>(R.id.mIvMainIcon)
            val mTvMainText = view.findViewById<TextView>(R.id.mTvMainText)

            mCvMainView.setBackgroundColor(it.color)
            mIvMainIcon.setImageResource(it.icon)
            mTvMainText.text = it.title

            mCvMainView.layoutParams?.let { lp ->
                lp.height = windowHeight / 5 * 3
            }


            // 点击事件
            view.setOnClickListener { _ ->
                when (it.icon) {
                    R.drawable.img_main_weather -> ARouterHelper.startActivity(ARouterHelper.PATH_WEATHER)
                    R.drawable.img_mian_contell -> ARouterHelper.startActivity(ARouterHelper.PATH_CONSTELLATION)
                    R.drawable.img_main_joke_icon -> ARouterHelper.startActivity(ARouterHelper.PATH_JOKE)
                    R.drawable.img_main_map_icon -> ARouterHelper.startActivity(ARouterHelper.PATH_MAP)
                    R.drawable.img_main_app_manager -> ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
                    R.drawable.img_main_voice_setting -> ARouterHelper.startActivity(ARouterHelper.PATH_VOICE_SETTING)
                    R.drawable.img_main_system_setting -> ARouterHelper.startActivity(ARouterHelper.PATH_SETTING)
                    R.drawable.img_main_developer -> ARouterHelper.startActivity(ARouterHelper.PATH_DEVELOPER)
                }
            }

            mListView.add(view)
        }
    }

    // 连接服务
    private fun linkService() {

        startService(Intent(this, VoiceService::class.java))
    }

}
