package viewModel

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.resetValue() {
    this.value = value
}