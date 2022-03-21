package viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    val titles: MutableLiveData<MutableList<String?>> by lazy {
        MutableLiveData<MutableList<String?>>(initTitles())
    }

    private fun initTitles(): MutableList<String?> {
        return mutableListOf(null, "Title 2", "Title 3")
    }

    fun addTitle(title: String) {
        titles.value?.add(title)
        titles.postValue(titles.value)
    }
}