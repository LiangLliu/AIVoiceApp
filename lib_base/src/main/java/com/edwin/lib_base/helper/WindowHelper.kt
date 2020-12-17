package com.edwin.lib_base.helper

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.WindowManager

/**
 * 窗口帮助类
 */
object WindowHelper {

    private lateinit var mContext: Context

    private lateinit var windowManager: WindowManager
    private lateinit var layoutParams: WindowManager.LayoutParams


    fun initHelper(mContext: Context) {
        this.mContext = mContext
        this.windowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        layoutParams = createLayoutParams()
    }


    /**
     * 创建布局属性
     */
    private fun createLayoutParams(): WindowManager.LayoutParams {
        val layoutParams = WindowManager.LayoutParams()

        layoutParams.apply {
            this.width = WindowManager.LayoutParams.MATCH_PARENT
            this.height = WindowManager.LayoutParams.MATCH_PARENT
            this.gravity = Gravity.CENTER
            format = PixelFormat.TRANSLUCENT

            gravity = Gravity.CENTER
            format = PixelFormat.TRANSLUCENT
            flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS

            type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            else
                WindowManager.LayoutParams.TYPE_PHONE
        }
        return layoutParams
    }


    /**
     * 获取视图
     */
    fun getView(layoutId: Int): View {
        return View.inflate(mContext, layoutId, null)
    }

    /**
     * 显示窗口
     */
    fun show(view: View) {
        if (view.parent == null) {
            this.windowManager.addView(view, this.layoutParams)
        }
    }


    /**
     * 显示视图，但是窗口属性自定义
     */
    fun show(view: View, layoutParams: WindowManager.LayoutParams) {
        if (view.parent == null) {
            this.windowManager.addView(view, layoutParams)
        }
    }


    /**
     * 隐藏窗口
     */
    fun hide(view: View) {
        if (view.parent != null) {
            this.windowManager.removeView(view)
        }
    }

    /**
     * 更新视图
     */
    fun update(view: View, layoutParams: WindowManager.LayoutParams) {
        this.windowManager.updateViewLayout(view, layoutParams)
    }
}