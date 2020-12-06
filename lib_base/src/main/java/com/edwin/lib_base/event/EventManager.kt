package com.edwin.lib_base.event

import org.greenrobot.eventbus.EventBus
import java.util.*

/**
 * Event 管理
 */
object EventManager {

    // 注册
    fun register(subscriber: Any) {
        EventBus.getDefault().register(subscriber)
    }

    // 解绑
    fun onRegister(subscriber: Any) {
        EventBus.getDefault().unregister(subscriber)
    }

    // 发送事件类
    private fun post(event: MessageEvent) {
        EventBus.getDefault().post(event)
    }

    // 发送事件
    fun post(type: Int) {
        post(MessageEvent(type))
    }

    // 发送类型，携带参数
    fun post(type: Int, stringValue: String) {
        val event = MessageEvent(type)
        event.stringValue = stringValue
        post(event)
    }

    // 发送类型，携带参数
    fun post(type: Int, intValue: Int) {
        val event = MessageEvent(type)
        event.intValue = intValue
        post(event)
    }

    // 发送类型，携带参数
    fun post(type: Int, booleanValue: Boolean) {
        val event = MessageEvent(type)
        event.booleanValue = booleanValue
        post(event)
    }

}