package com.example.simpleapplication.repository

interface DataRepository {
    fun saveData(value: String)
    fun getData(): String
}