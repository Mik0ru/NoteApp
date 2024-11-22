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
}