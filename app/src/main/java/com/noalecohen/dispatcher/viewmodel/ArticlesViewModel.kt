package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noalecohen.dispatcher.model.response.Article
import com.noalecohen.dispatcher.repository.ArticlesRepository
import com.noalecohen.dispatcher.viewstate.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticlesViewModel : ViewModel() {
    private val articlesRepository = ArticlesRepository()

    val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val searchArticlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val articlesStateLiveData: MutableLiveData<RequestState> = MutableLiveData(RequestState.Idle)
    val searchArticlesStateLiveData: MutableLiveData<RequestState> =
        MutableLiveData(RequestState.Idle)


    fun fetchTopHeadlinesByCountry(country: String) {
        articlesStateLiveData.postValue(RequestState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            articlesRepository.fetchTopHeadlinesByCountry(country).collect {
                if (it.isNullOrEmpty()) {
                    articlesLiveData.postValue(emptyList())
                    articlesStateLiveData.postValue(RequestState.Error("error..."))
                } else {
                    articlesLiveData.postValue(it)
                    articlesStateLiveData.postValue(RequestState.Success)
                }
            }
        }
    }

    fun fetchFilterResults(keyword: String) {
        searchArticlesStateLiveData.postValue(RequestState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            articlesRepository.fetchFilterResults(keyword).collect {
                if (it.isNullOrEmpty()) {
                    searchArticlesLiveData.postValue(emptyList())
                    searchArticlesStateLiveData.postValue(RequestState.Error("error..."))
                } else {
                    searchArticlesLiveData.postValue(it)
                    searchArticlesStateLiveData.postValue(RequestState.Success)
                }
            }

        }
    }

    fun resetArticlesStateLiveData() {
        articlesStateLiveData.postValue(RequestState.Idle)
    }

    fun resetSearchArticlesStateLiveData() {
        searchArticlesStateLiveData.postValue(RequestState.Idle)
    }

    fun resetSearchArticlesLiveData() {
        searchArticlesLiveData.postValue(emptyList())
    }

}