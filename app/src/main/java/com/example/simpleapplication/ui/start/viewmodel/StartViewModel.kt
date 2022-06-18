package com.example.simpleapplication.ui.start.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleapplication.repository.DataRepository
import com.example.simpleapplication.ui.start.StartEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    private val _state = MutableLiveData<UIState>()
    val state: LiveData<UIState>
        get() = _state


    fun onEvent(event: StartEvent) {
        when (event) {
            is StartEvent.TypedEvent -> {
                _data.value = event.value
            }
        }
    }

    fun save() {
        viewModelScope.launch {
            if (_data.value.isNullOrBlank()) {
                return@launch
            }
            dataRepository.saveData(_data.value!!)
            _state.value = UIState.Saved(_data.value!!)
        }
    }

    fun test() {
        dataRepository.getData()
    }

    init {
        measurePerformanceInMS {
            test()
        }
    }

    private inline fun <T> measurePerformanceInMS(
        function: () -> T
    )
            : T {
        val startTime = System.currentTimeMillis()
        val result: T = function.invoke()
        val endTime = System.currentTimeMillis()
        Log.d("TAG","PERFORMANCE IN MS: ${endTime - startTime} ms ")

        return result
    }
}

sealed class UIState() {
    data class Saved(val value: String) : UIState()
}