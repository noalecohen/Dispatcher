package viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import model.AppRepository
import viewstate.ViewState

class AuthViewModel : ViewModel() {
    private val appRepository = AppRepository()

    val viewStateLiveData: MutableLiveData<ViewState> = MutableLiveData(ViewState.Idle)

    fun register(email: String, password: String) {
        viewStateLiveData.postValue(ViewState.Loading)
        appRepository.register(email, password) { it ->
            if (it.isSuccessful) {
                it.result.user?.let {
                    viewStateLiveData.postValue(ViewState.Success(it))
                }
            } else {
                viewStateLiveData.postValue(ViewState.Error(it.exception))
            }
        }
    }

    fun login(email: String, password: String) {
        viewStateLiveData.postValue(ViewState.Loading)
        appRepository.login(email, password) { it ->
            if (it.isSuccessful) {
                it.result.user?.let {
                    viewStateLiveData.postValue(ViewState.Success(it))
                }
            } else {
                viewStateLiveData.postValue(ViewState.Error(it.exception))
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return appRepository.getCurrentUser()
    }
}