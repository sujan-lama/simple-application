package com.example.simpleapplication.data

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(private val sharedPreferences: SharedPreferences) {


    fun getString(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    fun saveString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

}