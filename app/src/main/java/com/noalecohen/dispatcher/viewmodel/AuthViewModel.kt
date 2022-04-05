package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.noalecohen.dispatcher.repository.AuthRepository
import com.noalecohen.dispatcher.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val appRepository = AuthRepository()

    val viewStateLiveDataRegister: MutableLiveData<ViewState> = MutableLiveData(ViewState.Idle)
    val viewStateLiveDataLogin: MutableLiveData<ViewState> = MutableLiveData(ViewState.Idle)

    fun register(email: String, password: String) {
        viewStateLiveDataRegister.postValue(ViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val result = appRepository.register(email, password)

            when (result) {
                is AuthRepository.Response.Success -> {
                    viewStateLiveDataRegister.postValue(result.authResult.user?.let {
                        ViewState.Success(it)
                    })
                }
                is AuthRepository.Response.Error -> {
                    viewStateLiveDataRegister.postValue(ViewState.Error(result.error))
                }
            }
        }
    }


    fun login(email: String, password: String) {
        viewStateLiveDataLogin.postValue(ViewState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val result = appRepository.login(email, password)

            when (result) {
                is AuthRepository.Response.Success -> {
                    viewStateLiveDataLogin.postValue(result.authResult.user?.let {
                        ViewState.Success(it)
                    })
                }
                is AuthRepository.Response.Error -> {
                    viewStateLiveDataLogin.postValue(ViewState.Error(result.error))
                }
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