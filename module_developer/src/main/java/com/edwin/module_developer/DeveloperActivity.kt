package com.edwin.module_developer

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.base.adapter.CommonAdapter
import com.edwin.lib_base.base.adapter.CommonViewHolder
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_voice.manager.VoiceManager
import com.edwin.module_developer.data.DeveloperListData
import kotlinx.android.synthetic.main.activity_developer.*
import java.util.ArrayList

/**
 * 开发者模式
 */

@Route(path = ARouterHelper.PATH_DEVELOPER)
class DeveloperActivity : BaseActivity() {

    //    标题
    private val mTypeTitle = 0;

    //    内容
    private val mTypeContent = 1

    private val mList = ArrayList<DeveloperListData>()


    override fun getLayoutId(): Int {
        return R.layout.activity_developer
    }

    override fun getTitleText(): String {
        return getString(com.edwin.lib_base.R.string.app_title_developer)
    }

    override fun isShowBack(): Boolean {
        return false
    }

    override fun initView() {
        initData()
        initListView()
    }


    private fun initData() {
        val dataArray = resources.getStringArray(com.edwin.lib_base.R.array.DeveloperListArray)
        dataArray.forEach {
            if (it.contains("[")) {
                addItemData(
                    mTypeTitle,
                    it.replace("[", "").replace("]", "")
                )
            } else {
                addItemData(mTypeContent, it)
            }
        }
    }

    private fun initListView() {
        mRvDeveloperView.layoutManager = LinearLayoutManager(this)

        //  分割线
        mRvDeveloperView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        //  适配器
        mRvDeveloperView.adapter = CommonAdapter(mList,
            object : CommonAdapter.OnMoreBindDataListener<DeveloperListData> {
                override fun onBindViewHolder(
                    model: DeveloperListData,
                    viewHolder: CommonViewHolder,
                    type: Int,
                    position: Int
                ) {
                    when (model.type) {
                        mTypeTitle -> viewHolder.setText(R.id.mTvDeveloperTitle, model.text)


                        mTypeContent -> {
                            viewHolder.setText(
                                R.id.mTvDeveloperContent,
                                "${position}.${model.text}"
                            )

                            viewHolder.itemView.setOnClickListener {
                                itemClick(position)
                            }
                        }

                    }
                }

                override fun getLayoutId(type: Int): Int {
                    return if (type == mTypeTitle) {
                        R.layout.layout_developer_title
                    } else {
                        R.layout.layout_developer_content
                    }
                }

                override fun getItemType(position: Int): Int {
                    return mList[position].type
                }

            })


    }

    //    添加数据
    private fun addItemData(type: Int, text: String) {
        mList.add(DeveloperListData(type, text))
    }

    //    点击事件
    private fun itemClick(position: Int) {
        when (position) {
            1 -> ARouterHelper.startActivity(ARouterHelper.PATH_APP_MANAGER)
            2 -> ARouterHelper.startActivity(ARouterHelper.PATH_CONSTELLATION)
            3 -> ARouterHelper.startActivity(ARouterHelper.PATH_JOKE)
            4 -> ARouterHelper.startActivity(ARouterHelper.PATH_MAP)
            5 -> ARouterHelper.startActivity(ARouterHelper.PATH_SETTING)
            6 -> ARouterHelper.startActivity(ARouterHelper.PATH_VOICE_SETTING)
            7 -> ARouterHelper.startActivity(ARouterHelper.PATH_WEATHER)

            21 -> VoiceManager.start("你好，我是小爱同学")
            22 -> VoiceManager.pause()
            23 -> VoiceManager.resume()
            24 -> VoiceManager.stop()
            25 -> VoiceManager.release()


        }
    }

}