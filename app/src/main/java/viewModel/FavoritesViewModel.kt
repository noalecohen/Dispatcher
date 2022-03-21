package viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {
    val authors: MutableLiveData<List<String?>> by lazy {
        MutableLiveData<List<String?>>(initAuthors())
    }

    private fun initAuthors(): List<String?> {
        return listOf(null, "Ben Cohen", "Omri Ovadia")
    }
}