package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noalecohen.dispatcher.model.response.Article
import com.noalecohen.dispatcher.repository.ArticlesRepository
import com.noalecohen.dispatcher.viewstate.RequestState

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

        articlesRepository.fetchTopHeadlinesByCountry(country) { result, error ->
            if (result.isNotEmpty()) {
                articlesLiveData.postValue(result)
                articlesStateLiveData.postValue(RequestState.Success)

            } else {
                if (error == null) {
                    articlesStateLiveData.postValue(RequestState.Success)
                } else {
                    articlesStateLiveData.postValue(error.let { RequestState.Error(it) })
                }
            }
        }
    }

    fun fetchFilterResults(keyword: String) {

        articlesRepository.fetchFilterResults(keyword) { result, error ->
            if (result.isNotEmpty()) {
                searchArticlesLiveData.postValue(result)
                searchArticlesStateLiveData.postValue(RequestState.Success)
            } else {
                if (error == null) {
                    searchArticlesLiveData.postValue(emptyList())
                    searchArticlesStateLiveData.postValue(RequestState.Success)
                } else {
                    searchArticlesLiveData.postValue(emptyList())
                    searchArticlesStateLiveData.postValue(error.let { RequestState.Error(it) })
                }
            }
        }

    }

    companion object {
        const val DEFAULT_COUNTRY_CODE = "us"
    }
}