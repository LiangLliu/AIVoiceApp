package com.edwin.module_weather

import android.os.Bundle
import com.edwin.lib_base.base.BaseActivity

class WeatherActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
    }

}