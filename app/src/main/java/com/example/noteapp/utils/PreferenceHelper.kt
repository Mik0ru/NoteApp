package com.example.noteapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    private lateinit var sharedPreference: SharedPreferences

    fun unit(context: Context){
        sharedPreference = context.getSharedPreferences("sharedPreference",Context.MODE_PRIVATE)
    }

    var isOnboard:Boolean
        get() = sharedPreference.getBoolean("isOnboard", true)
        set(value: Boolean) = sharedPreference.edit().putBoolean("isOnboard", value).apply()

    var isLogged:Boolean
        get() = sharedPreference.getBoolean("isLogged", false)
        set(value) = sharedPreference.edit().putBoolean("isLogged",value).apply()

    var isLinear: Boolean
        get() = sharedPreference.getBoolean("isLinear", true)
        set(value) = sharedPreference.edit().putBoolean("isLinear", value).apply()
}