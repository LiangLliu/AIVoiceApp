package com.edwin.module_voice_setting

import android.util.Log
import android.widget.SeekBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.edwin.lib_base.base.BaseActivity
import com.edwin.lib_base.base.adapter.CommonAdapter
import com.edwin.lib_base.base.adapter.CommonViewHolder
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_voice.manager.VoiceManager
import kotlinx.android.synthetic.main.activity_voice_setting.*

/**
 * 语音设置
 */
@Route(path = ARouterHelper.PATH_VOICE_SETTING)
class VoiceSettingActivity : BaseActivity() {

    private val mList: ArrayList<String> = ArrayList()

    private var mTTSPeopleIndex: Array<String>? = null

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
        // 默认值
        bar_voice_speed.progress = 5
        bar_voice_volume.progress = 5

        // 设置最大值
        bar_voice_speed.max = 15
        bar_voice_volume.max = 15

        initData()
        initListener()
        initPeopleView()

        btn_test.setOnClickListener {
            VoiceManager.ttsStart("大家好，我是小爱")
        }

    }


    // 初始化数据
    private fun initData() {
        val mTTSPeople = resources.getStringArray(R.array.TTSPeople)
        mTTSPeopleIndex = resources.getStringArray(R.array.TTSPeopleIndex)

        mTTSPeople.forEach {
            mList.add(it)
        }

    }

    // 初始化发音人列表
    private fun initPeopleView() {
        rv_voice_people.layoutManager = LinearLayoutManager(this)
        rv_voice_people.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        rv_voice_people.adapter =
            CommonAdapter(mList, object : CommonAdapter.OnBindDataListener<String> {
                override fun onBindViewHolder(
                    model: String, viewHolder: CommonViewHolder, type: Int, position: Int
                ) {
                    viewHolder.setText(R.id.mTvPeopleContent, model)

                    viewHolder.itemView.setOnClickListener {
                        mTTSPeopleIndex?.let {
                            VoiceManager.setPeople(it[position])
                        }
                    }
                }

                override fun getLayoutId(type: Int): Int {
                    return R.layout.layout_tts_people_list
                }

            })
    }

    // 初始化监听
    private fun initListener() {
        // 语速监听
        bar_voice_speed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                bar_voice_speed.progress = progress
                Log.d("音量", progress.toString())
                VoiceManager.setVoiceSpeed(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })


        // 音量监听
        bar_voice_volume.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                bar_voice_volume.progress = progress
                VoiceManager.setVoiceVolume(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
}