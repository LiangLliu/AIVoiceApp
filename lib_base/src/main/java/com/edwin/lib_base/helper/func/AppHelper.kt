package com.edwin.lib_base.helper.func

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.view.View
import com.edwin.lib_base.helper.func.data.AppData
import com.edwin.lib_base.utils.L

/**
 * 应用帮助类
 */
object AppHelper {

    /**
     * 上下文
     */
    private lateinit var mContext: Context

    /**
     * 包管理器
     */
    private lateinit var mPackageManager: PackageManager


    //所有应用
    private val mAllAppList = ArrayList<AppData>()

    //所有商店包名
    private lateinit var mAllMarkArray: Array<String>

    //分页View
    val mAllViewList = ArrayList<View>()

    /**
     * 初始化完成
     */
    fun initHelper(context: Context) {
        this.mContext = context
        mPackageManager = mContext.packageManager
        loadAllApp()
    }

    /**
     * 加载所有的APP
     */
    private fun loadAllApp() {

        val intent = Intent(Intent.ACTION_MAIN, null)

        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val queryIntentActivities = mPackageManager.queryIntentActivities(intent, 0)

        queryIntentActivities.forEachIndexed { _, resolveInfo ->
            val appData = AppData(
                resolveInfo.activityInfo.packageName,
                resolveInfo.loadLabel(mPackageManager) as String,
                resolveInfo.loadIcon(mPackageManager),
                resolveInfo.activityInfo.name,
                (resolveInfo.activityInfo.flags and ApplicationInfo.FLAG_SYSTEM) > 0
            )
            mAllAppList.add(appData)
        }

        L.e("mAllAppList:$mAllAppList")
    }

}