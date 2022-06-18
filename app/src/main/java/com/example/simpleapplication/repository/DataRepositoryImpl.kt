package com.example.simpleapplication.repository

import com.example.simpleapplication.constants.SHARED
import com.example.simpleapplication.data.SharedPreferenceHelper

class DataRepositoryImpl(private val sharedPreferenceHelper: SharedPreferenceHelper) :
    DataRepository {
    override fun saveData(value: String) {
        sharedPreferenceHelper.saveString(SHARED.KEY, value)
    }

    override fun getData(): String {
        return sharedPreferenceHelper.getString(SHARED.KEY)
    }
}