package com.edwin.aivoiceapp.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edwin.aivoiceapp.R
import com.edwin.aivoiceapp.data.ChatList
import com.edwin.aivoiceapp.entity.AppConstants
import com.edwin.lib_base.helper.NotificationHelper
import com.edwin.lib_base.helper.WindowHelper
import com.edwin.lib_base.utils.L
import com.edwin.lib_voice.engine.VoiceEngineAnalyze
import com.edwin.lib_voice.impl.OnAsrResultListener
import com.edwin.lib_voice.impl.OnNluResultListener
import com.edwin.lib_voice.manager.VoiceManager
import com.edwin.lib_voice.tts.VoiceTTS
import com.edwin.lib_voice.words.WordTools
import com.imooc.aivoiceapp.adapter.ChatListAdapter
import org.json.JSONObject

class VoiceService : Service(), OnNluResultListener {

    private val mHandler = Handler()

    private lateinit var mFullWindowView: View
    private lateinit var mChatListView: RecyclerView
    private val mList = ArrayList<ChatList>()
    private lateinit var mChatAdapter: ChatListAdapter

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        L.i("语音服务启动")
        initCoreVoiceService()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        bindNotification()
        return START_STICKY_COMPATIBILITY
    }

    //    绑定通知栏
    private fun bindNotification() {
        startForeground(1000, NotificationHelper.bindVoiceService("正在运行"))
    }


    private fun initCoreVoiceService() {

        WindowHelper.initHelper(this)

        mFullWindowView = WindowHelper.getView(R.layout.layout_window_item)
        mChatListView = mFullWindowView.findViewById<RecyclerView>(R.id.mChatListView)

        // 设置属性
        mChatListView.layoutManager = LinearLayoutManager(this)

        // 设置适配器
        mChatAdapter = ChatListAdapter(mList);
        mChatListView.adapter = mChatAdapter


        VoiceManager.initManager(this, object : OnAsrResultListener {
            override fun wakeUpReady() {
                L.i("唤醒准备就绪")
                VoiceManager.ttsStart("唤醒引擎准备就绪")
            }

            override fun asrStartSpeak() {
                L.i("开始播放")
            }

            override fun asrStopSpeak() {
                L.i("结束说话")
                hideWindow()
            }

            override fun wakeUpSuccess(result: JSONObject) {
                L.i("唤醒成功：$result")
                // 当唤醒词是小爱，才开启
                val errorCode = result.optInt("errorCode")
                if (0 == errorCode) {
                    // 唤醒成功
                    val wakeUpWord = result.optString("word")

                    if (wakeUpWord == "小爱同学" || wakeUpWord == "小袁同学" || wakeUpWord == "小袁小袁") {

                        showWindow()

                        // 应答
                        val wakeUpText = WordTools.wakeUpWords()
                        addMineText(wakeUpText)
                        VoiceManager.ttsStart(wakeUpText,
                            object : VoiceTTS.OnTTSResultListener {
                                override fun ttsEnd() {
                                    // 开启识别
                                    VoiceManager.startAsr()
                                }
                            })
                    }
                }
            }

            override fun asrResult(result: JSONObject) {
                L.i("=======================result=========================")
                L.i("result： $result")
            }

            override fun nluResult(nlu: JSONObject) {
                L.i("=======================nlu=========================")
                L.i("nlu： $nlu")
                addAIText(nlu.toString())
                VoiceEngineAnalyze.analyzeNlu(nlu, this@VoiceService)
            }

            override fun voiceError(error: String) {
                L.i("发生错误：$error")
                hideWindow()
            }
        })
    }


    /**
     * 显示窗口
     */
    private fun showWindow() {
        L.i("=============显示窗口===========")
        WindowHelper.show(mFullWindowView)
    }


    /**
     * 隐藏窗口
     */
    private fun hideWindow() {
        L.i("=============隐藏窗口===========")
        mHandler.postDelayed({ WindowHelper.hide(mFullWindowView) }, 2 * 1000)

    }

    // 查询天气
    override fun queryWeather() {

    }

    /**
     * 添加我的的文本
     */
    private fun addMineText(text: String) {
        val bean = ChatList(AppConstants.TYPE_MINE_TEXT)
        bean.text = text
        baseAddItem(bean)
    }

    /**
     * 添加AI文本
     */
    private fun addAIText(text: String) {
        val bean = ChatList(AppConstants.TYPE_AI_TEXT)
        bean.text = text
        baseAddItem(bean)
    }

    /**
     * 添加基类
     */
    private fun baseAddItem(bean: ChatList) {
        mList.add(bean)
        mChatAdapter.notifyItemInserted(mList.size - 1)
    }


}