package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noalecohen.dispatcher.model.response.Article
import com.noalecohen.dispatcher.repository.ArticlesRepository
import com.noalecohen.dispatcher.viewstate.RequestState

class ArticlesViewModel : ViewModel() {
    private val articlesRepository = ArticlesRepository()

    val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()
    val requestStateLiveData: MutableLiveData<RequestState> = MutableLiveData(RequestState.Idle)

    fun fetchTopHeadlinesByCountry(country: String) {

        articlesRepository.fetchTopHeadlinesByCountry(country) { result, error ->
            if (result.isNotEmpty()) {
                articlesLiveData.postValue(result)
                requestStateLiveData.postValue(RequestState.Success)

            } else {
                if (error == null) {
                    requestStateLiveData.postValue(RequestState.Success)
                } else {
                    requestStateLiveData.postValue(error.let { RequestState.Error(it) })
                }
            }

        }
    }
}