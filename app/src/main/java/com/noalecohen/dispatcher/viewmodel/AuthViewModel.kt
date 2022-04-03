package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.noalecohen.dispatcher.repository.AuthRepository
import com.noalecohen.dispatcher.viewstate.ViewState

class AuthViewModel : ViewModel() {
    private val appRepository = AuthRepository()

    val viewStateLiveDataRegister: MutableLiveData<ViewState> = MutableLiveData(ViewState.Idle)
    val viewStateLiveDataLogin: MutableLiveData<ViewState> = MutableLiveData(ViewState.Idle)

    fun register(email: String, password: String) {
        viewStateLiveDataRegister.postValue(ViewState.Loading)
        appRepository.register(email, password) { it ->
            if (it.isSuccessful) {
                it.result.user?.let {
                    viewStateLiveDataRegister.postValue(ViewState.Success(it))
                }
            } else {
                viewStateLiveDataRegister.postValue(ViewState.Error(it.exception))
            }
        }
    }

    fun login(email: String, password: String) {
        viewStateLiveDataLogin.postValue(ViewState.Loading)
        appRepository.login(email, password) { it ->
            if (it.isSuccessful) {
                it.result.user?.let {
                    viewStateLiveDataLogin.postValue(ViewState.Success(it))
                }
            } else {
                viewStateLiveDataLogin.postValue(ViewState.Error(it.exception))
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return appRepository.getCurrentUser()
    }

    fun signOut() {
        appRepository.signOut()
    }
}