package com.edwin.lib_base.base

import android.app.Application
import com.edwin.lib_base.helper.ARouterHelper

/**
 * 基类APP
 */
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()


        ARouterHelper.initHelper(this)
    }
}