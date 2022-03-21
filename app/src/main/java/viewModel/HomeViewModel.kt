package viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val bodies: MutableLiveData<List<String?>> by lazy {
        MutableLiveData<List<String?>>(initBodies())
    }

    private fun initBodies(): List<String?> {
        return listOf(null, "Body 2 Text", "3")
    }
}