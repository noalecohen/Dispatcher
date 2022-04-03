package com.noalecohen.dispatcher.viewstate

open class RequestState {
    object Idle : RequestState()
    object Loading : RequestState()
    data class Error(val error: String) : RequestState()
    object Success : RequestState()
}