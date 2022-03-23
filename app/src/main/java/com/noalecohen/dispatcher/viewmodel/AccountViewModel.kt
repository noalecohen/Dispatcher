package com.noalecohen.dispatcher.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val NEW_INSTANCE_INDICATOR = "Instance Indicator"

class AccountViewModel : ViewModel() {
    val titles: MutableLiveData<MutableList<String?>> by lazy {
        MutableLiveData<MutableList<String?>>(initTitles())
    }

    private fun initTitles(): MutableList<String?> {
        Log.d(NEW_INSTANCE_INDICATOR, "New instance of AccountViewModel created")
        return mutableListOf(null, "Title 2", "Title 3")
    }

    fun addTitle(title: String) {
        titles.value?.add(title)
        titles.resetValue()
    }
}