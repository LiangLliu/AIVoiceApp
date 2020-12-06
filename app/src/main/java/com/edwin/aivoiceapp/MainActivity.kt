package com.edwin.aivoiceapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edwin.lib_base.event.EventManager
import com.edwin.lib_base.event.MessageEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventManager.register(this)

        btn1.setOnClickListener {
            EventManager.post(1111)
        }

        btn2.setOnClickListener {
            EventManager.post(2222, "hello EventBus")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        EventManager.onRegister(this)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        when (event.type) {
            1111 -> Log.i("TestApp", "1111")
            2222 -> Log.i("TestApp", event.stringValue)
        }
    }
}