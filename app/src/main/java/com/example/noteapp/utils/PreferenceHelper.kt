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

    var Layout: Int
        get() = sharedPreference.getInt("Layout", 1)
        set(value) = sharedPreference.edit().putInt("Layout", value)!!.apply()
}