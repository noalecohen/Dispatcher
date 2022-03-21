package viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    val titles: MutableLiveData<List<String?>> by lazy {
        MutableLiveData<List<String?>>(initTitles())
    }

    private fun initTitles(): List<String?> {
        return listOf(null, "Title 2", "Title 3")
    }

    fun addTitle(title: String) {
        val list = titles.value
        titles.postValue(list?.plus(title))
    }
}