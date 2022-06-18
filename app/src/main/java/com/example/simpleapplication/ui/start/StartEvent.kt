package com.example.simpleapplication.ui.start

sealed class StartEvent {
    data class TypedEvent(val value: String) : StartEvent()
}
