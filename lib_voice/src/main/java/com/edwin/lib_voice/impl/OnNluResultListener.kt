package com.edwin.lib_voice.impl

/**
 * 语义结果
 */
interface OnNluResultListener {


    //=======================App 操作=======================

    //打开App
    fun openApp(appName: String)

    //卸载App
    fun unInstallApp(appName: String)

    //其他App
    fun otherApp(appName: String)


    /**
     * 查询天气
     */
    fun queryWeather()


    //=======================其他=======================

    //听不懂你的话
    fun nluError()

}