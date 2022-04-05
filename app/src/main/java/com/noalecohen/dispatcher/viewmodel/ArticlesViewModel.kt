package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noalecohen.dispatcher.model.response.Article
import com.noalecohen.dispatcher.repository.ArticlesRepository
import com.noalecohen.dispatcher.viewstate.RequestState
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {
    private val articlesRepository = ArticlesRepository()

    val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val searchArticlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val articlesStateLiveData: MutableLiveData<RequestState> = MutableLiveData(RequestState.Idle)
    val searchArticlesStateLiveData: MutableLiveData<RequestState> =
        MutableLiveData(RequestState.Idle)

    init {
        fetchTopHeadlinesByCountry(DEFAULT_COUNTRY_CODE)
    }

    fun fetchTopHeadlinesByCountry(country: String) {

        viewModelScope.launch {
            val result = articlesRepository.fetchTopHeadlinesByCountry(country)

            if (result.isNullOrEmpty()) {
                articlesLiveData.postValue(emptyList())
                articlesStateLiveData.postValue(RequestState.Error("error..."))
            } else {
                articlesLiveData.postValue(result)
                articlesStateLiveData.postValue(RequestState.Success)
            }
        }

    }

    fun fetchFilterResults(keyword: String) {

        viewModelScope.launch {
            val result = articlesRepository.fetchFilterResults(keyword)

            if (result.isNullOrEmpty()) {
                searchArticlesLiveData.postValue(emptyList())
                searchArticlesStateLiveData.postValue(RequestState.Error("error..."))
            } else {
                searchArticlesLiveData.postValue(result)
                searchArticlesStateLiveData.postValue(RequestState.Success)
            }
        }

    }

    companion object {
        const val DEFAULT_COUNTRY_CODE = "us"
    }
}