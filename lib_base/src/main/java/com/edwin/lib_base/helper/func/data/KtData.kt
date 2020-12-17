package com.edwin.lib_base.helper.func.data

import android.graphics.drawable.Drawable

/**
 * 包名
 * 应用名称
 * ICON
 * 第一启动类
 * 是否是系统应用
 */
data class AppData(
    val packName: String,
    val appName: String,
    val appIcon: Drawable,
    val firstRunName: String,
    val isSystemApp: Boolean
)

//联系人
data class ContactData(
    val phoneName: String,
    val phoneNumber: String
)