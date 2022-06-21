package com.example.mental_health_app

import android.app.Application
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initPython()
    }
    private fun initPython(){
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(this));
        }

    }

}