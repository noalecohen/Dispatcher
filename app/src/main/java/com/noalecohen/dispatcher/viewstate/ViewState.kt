package com.noalecohen.dispatcher.viewstate

import com.google.firebase.auth.FirebaseUser

open class ViewState {
    object Idle : ViewState()
    object Loading : ViewState()
    data class Error(val error: Exception?) : ViewState()
    data class Success(val user: FirebaseUser) : ViewState()
}