package com.example.simpleapplication.data

import android.content.Context
import android.content.SharedPreferences
import com.example.simpleapplication.constants.SHARED
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedPreferenceHelperTest {

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var sharedPreferenceHelper: SharedPreferenceHelper

    @Before
    fun setup() {
        sharedPreferences = createMockSharedPreference()
        Mockito.`when`(context.getSharedPreferences(SHARED.NAME, Context.MODE_PRIVATE))
            .thenReturn(sharedPreferences)
        sharedPreferenceHelper = SharedPreferenceHelper(sharedPreferences)
    }

    @Test
    fun saveTest() {
        sharedPreferenceHelper.saveString(SHARED.KEY, "sujan")
        val value = sharedPreferenceHelper.getString(SHARED.KEY)
        assertThat(value, `is`(equals("sujan")))
    }

    private fun createMockSharedPreference(): SharedPreferences {
        Mockito.`when`(editor.apply()).thenReturn(null)
        return sharedPreferences
    }

}