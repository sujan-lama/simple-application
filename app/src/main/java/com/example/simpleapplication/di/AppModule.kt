package com.example.simpleapplication.di

import android.app.Application
import android.content.Context
import com.example.simpleapplication.constants.SHARED
import com.example.simpleapplication.data.SharedPreferenceHelper
import com.example.simpleapplication.repository.DataRepository
import com.example.simpleapplication.repository.DataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesSharedPreference(app: Application): SharedPreferenceHelper {
        return SharedPreferenceHelper(
            app.getSharedPreferences(SHARED.NAME, Context.MODE_PRIVATE)
        )
    }

    @Provides
    @Singleton
    fun providesDataRepository(sharedPreferenceHelper: SharedPreferenceHelper): DataRepository {
        return DataRepositoryImpl(sharedPreferenceHelper)
    }
}