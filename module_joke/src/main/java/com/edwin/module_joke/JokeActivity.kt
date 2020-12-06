package com.edwin.module_joke

import android.os.Bundle
import com.edwin.lib_base.base.BaseActivity

class JokeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.joke_activity)
    }

}