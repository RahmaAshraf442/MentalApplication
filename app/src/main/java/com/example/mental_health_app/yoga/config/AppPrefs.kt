package com.example.paytabs_demo_store_android.onboarding.config

import android.content.Context
import android.content.SharedPreferences


class AppPrefs(context: Context) {

    private  var prefs: SharedPreferences
    private  var prefsEditor: SharedPreferences.Editor

    init {
        prefs = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        prefsEditor = prefs.edit()
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        prefsEditor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        prefsEditor.commit()
    }

    fun isFirstTimeLaunch(): Boolean = prefs.getBoolean(IS_FIRST_TIME_LAUNCH, true)

    companion object {
        private const val PRIVATE_MODE = 0    // Shared preference mode
        private const val PREF_NAME = "app-prefs"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    }
}