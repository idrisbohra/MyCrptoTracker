package com.idris.crptotracker.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtils  {

    companion object{

        const val PREF_NAME = "crptoPref"
        const val PREF_CURRENT_RATE = "pref_current_rate"
        const val PREF_MIN_RATE = "pref_min_rate"
        const val PREF_MAX_RATE = "pref_max_rate"

        fun savePreference(context: Context, key: String, value: String) {
            val appSharedPrefs: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val prefsEditor = appSharedPrefs.edit()
            prefsEditor.putString(key, value)
            prefsEditor.apply()
        }

        fun saveFloatPreference(context: Context, key: String, value: Float) {
            val appSharedPrefs: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val prefsEditor = appSharedPrefs.edit()
            prefsEditor.putFloat(key, value)
            prefsEditor.apply()
        }

        fun getStringPreference(context: Context, key: String): String {
            val appSharedPrefs: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return appSharedPrefs.getString(key, "")!!
        }

        fun getFloatPreference(context: Context, key: String): Float {
            val appSharedPrefs: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return appSharedPrefs.getFloat(key, 0f)!!
        }
    }
}