package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {
    val authors: MutableLiveData<MutableList<String?>> by lazy {
        MutableLiveData<MutableList<String?>>(initAuthors())
    }

    private fun initAuthors(): MutableList<String?> {
        return mutableListOf(null, "Ben Cohen", "Omri Ovadia")
    }

    fun addAuthor(author: String) {
        authors.value?.add(author)
        authors.resetValue()
    }
}