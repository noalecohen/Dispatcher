package viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val bodies: MutableLiveData<MutableList<String?>> by lazy {
        MutableLiveData<MutableList<String?>>(initBodies())
    }

    private fun initBodies(): MutableList<String?> {
        return mutableListOf(null, "Body 2 Text", "3")
    }

    fun addBody(body: String) {
        bodies.value?.add(body)
        bodies.notifyObserver()
    }
}