package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.noalecohen.dispatcher.repository.AuthRepository
import com.noalecohen.dispatcher.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val appRepository = AuthRepository()

    val viewStateLiveDataRegister: MutableLiveData<ViewState> = MutableLiveData(ViewState.Idle)
    val viewStateLiveDataLogin: MutableLiveData<ViewState> = MutableLiveData(ViewState.Idle)

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _verifyPassword = MutableStateFlow("")

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

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setVerifyPassword(verifyPassword: String) {
        _verifyPassword.value = verifyPassword
    }

    val isSubmitEnabled: Flow<Boolean> =
        combine(_email, _password, _verifyPassword) { email, password, verifyPassword ->
            val isValidEmail =
                (email.length >= 3) && (email.contains("@") && (!email.startsWith("@")) && (!email.endsWith(
                    "@"
                )))
            val isValidPassword = password.length >= 6
            val isValidVerifyPassword = verifyPassword.length >= 6
            val isEqualPasswords = password == verifyPassword

            return@combine isValidEmail and isValidPassword and isValidVerifyPassword and isEqualPasswords
        }

    fun resetViewStateLiveDataLogin() {
        viewStateLiveDataLogin.postValue(ViewState.Idle)
    }

    fun resetViewStateLiveDataRegister() {
        viewStateLiveDataRegister.postValue(ViewState.Idle)
    }

}