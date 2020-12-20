package com.edwin.aivoiceapp.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.edwin.aivoiceapp.R
import com.edwin.aivoiceapp.data.ChatList
import com.edwin.aivoiceapp.entity.AppConstants
import com.edwin.lib_base.helper.NotificationHelper
import com.edwin.lib_base.helper.SoundPoolHelper
import com.edwin.lib_base.helper.WindowHelper
import com.edwin.lib_base.helper.func.AppHelper
import com.edwin.lib_base.utils.L
import com.edwin.lib_voice.engine.VoiceEngineAnalyze
import com.edwin.lib_voice.impl.OnAsrResultListener
import com.edwin.lib_voice.impl.OnNluResultListener
import com.edwin.lib_voice.manager.VoiceManager
import com.edwin.lib_voice.tts.VoiceTTS
import com.edwin.lib_voice.words.WordTools
import com.edwin.aivoiceapp.adapter.ChatListAdapter
import com.edwin.lib_base.helper.ARouterHelper
import com.edwin.lib_base.utils.SpUtils
import com.edwin.lib_network.HttpManager
import com.edwin.lib_network.bean.WeatherData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoiceService : Service(), OnNluResultListener {

    private val mHandler = Handler()

    private lateinit var mFullWindowView: View
    private lateinit var mChatListView: RecyclerView
    private lateinit var mLottieView: LottieAnimationView
    private lateinit var tvVoiceTips: TextView
    private lateinit var ivCloseWindow: ImageView
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

        mLottieView = mFullWindowView.findViewById<LottieAnimationView>(R.id.mLottieView)
        tvVoiceTips = mFullWindowView.findViewById<TextView>(R.id.tvVoiceTips)

        // 设置属性
        mChatListView.layoutManager = LinearLayoutManager(this)

        // 设置适配器
        mChatAdapter = ChatListAdapter(mList);
        mChatListView.adapter = mChatAdapter



        VoiceManager.initManager(this, object : OnAsrResultListener {
            override fun wakeUpReady() {
                L.i("唤醒准备就绪")

                //发声人
                VoiceManager.setPeople(
                    resources.getStringArray(R.array.TTSPeopleIndex)[SpUtils.getInt(
                        "tts_people",
                        0
                    )]
                )
                //语速
                VoiceManager.setVoiceSpeed(SpUtils.getInt("tts_speed", 5).toString())
                //音量
                VoiceManager.setVoiceVolume(SpUtils.getInt("tts_volume", 5).toString())

                val isHello = SpUtils.getBoolean("isHello", true)
                if (isHello) {
                    addAIText("唤醒引擎准备就绪")
                }
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

                    if (wakeUpWord == "小爱同学" || wakeUpWord == "小爱小爱") {
                        wakeUpFix()
                    }
                }
            }

            override fun updateUserText(text: String) {
                updateTips(text)
            }


            override fun asrResult(result: JSONObject) {
                L.i("=======================result=========================")
                L.i("result： $result")
            }

            override fun nluResult(nlu: JSONObject) {
                L.i("=======================nlu=========================")
                L.i("nlu： $nlu")
                addMineText(nlu.optString("raw_text"))
                VoiceEngineAnalyze.analyzeNlu(nlu, this@VoiceService)
            }

            override fun voiceError(error: String) {
                L.i("发生错误：$error")
                hideWindow()
            }
        })
    }

    /**
     * 唤醒成功之后的操作
     */
    private fun wakeUpFix() {
        showWindow()
        updateTips(getString(R.string.text_voice_wakeup_tips))
        SoundPoolHelper.play(R.raw.record_start)
        //应答
        val wakeupText = WordTools.wakeupWords()
        addAIText(wakeupText,
            object : VoiceTTS.OnTTSResultListener {
                override fun ttsEnd() {
                    //开启识别
                    VoiceManager.startAsr()
                }
            })
    }


    /**
     * 显示窗口
     */
    private fun showWindow() {
        L.i("=============显示窗口===========")
        mLottieView.playAnimation()
        WindowHelper.show(mFullWindowView)
    }


    /**
     * 隐藏窗口
     */
    private fun hideWindow() {
        L.i("=============隐藏窗口===========")
        mHandler.postDelayed({
            WindowHelper.hide(mFullWindowView)
            mLottieView.pauseAnimation()
            SoundPoolHelper.play(R.raw.record_over)
        }, 3 * 1000)

    }

    //打开APP
    override fun openApp(appName: String) {
        if (!TextUtils.isEmpty(appName)) {
            L.i("Open App $appName")
            val isOpen = AppHelper.launcherApp(appName)
            if (isOpen) {
                addAIText(getString(R.string.text_voice_app_open, appName))
            } else {
                addAIText(getString(R.string.text_voice_app_not_open, appName))
            }
        }
        hideWindow()
    }

    override fun unInstallApp(appName: String) {

        if (!TextUtils.isEmpty(appName)) {
            L.i("unInstall App $appName")
            val isUninstall = AppHelper.unInstallApp(appName)
            if (isUninstall) {
                addAIText(getString(R.string.text_voice_app_uninstall, appName))
            } else {
                addAIText(getString(R.string.text_voice_app_not_uninstall))
            }
        }
        hideWindow()
    }

    /**
     * 其他APP
     */
    override fun otherApp(appName: String) {

        //全部跳转应用市场
        if (!TextUtils.isEmpty(appName)) {
            val isIntent = AppHelper.launcherAppStore(appName)
            if (isIntent) {
                addAIText(getString(R.string.text_voice_app_option, appName))
            } else {
                addAIText(WordTools.noAnswerWords())
            }
        }
        hideWindow()
    }

    override fun queryWeather(city: String) {
        HttpManager.run {
            queryWeather(city, object : Callback<WeatherData> {
                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    addAIText(getString(R.string.text_voice_query_weather_error, city))
                    hideWindow()
                }

                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            it.result.realtime.apply {
                                //在UI上显示
                                addWeather(
                                    city,
                                    wid,
                                    info,
                                    temperature,
                                    object : VoiceTTS.OnTTSResultListener {
                                        override fun ttsEnd() {
                                            hideWindow()
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            })
        }
    }

    override fun queryWeatherInfo(city: String) {
        addAIText(getString(R.string.text_voice_query_weather, city))
        ARouterHelper.startActivity(ARouterHelper.PATH_WEATHER, "city", city)
        hideWindow()
    }

    override fun nluError() {
        //暂不支持
        addAIText(WordTools.noSupportWords())
        hideWindow()
    }

    /**
     * 添加我的文本
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
        VoiceManager.ttsStart(text)
    }


    /**
     * 添加AI文本
     */
    private fun addAIText(text: String, mOnTTSResultListener: VoiceTTS.OnTTSResultListener) {
        val bean = ChatList(AppConstants.TYPE_AI_TEXT)
        bean.text = text
        baseAddItem(bean)
        VoiceManager.ttsStart(text, mOnTTSResultListener)
    }


    /**
     * 添加天气
     */
    private fun addWeather(
        city: String, wid: String, info: String,
        temperature: String, mOnTTSResultListener: VoiceTTS.OnTTSResultListener
    ) {
        val bean = ChatList(AppConstants.TYPE_AI_WEATHER)
        bean.city = city
        bean.wid = wid
        bean.info = info
        bean.temperature = "$temperature°"
        baseAddItem(bean)
        val text = city + "今天天气" + info + temperature + "°"
        VoiceManager.ttsStart(text, mOnTTSResultListener)
    }

    /**
     * 添加基类
     */
    private fun baseAddItem(bean: ChatList) {
        mList.add(bean)
        mChatAdapter.notifyItemInserted(mList.size - 1)
    }

    /**
     * 更新提示语
     */
    private fun updateTips(text: String) {
        tvVoiceTips.text = text
    }


}