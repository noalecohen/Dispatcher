package com.noalecohen.dispatcher.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noalecohen.dispatcher.model.Article
import com.noalecohen.dispatcher.repository.ArticlesRepository

class ArticlesViewModel : ViewModel() {
    private val articlesRepository = ArticlesRepository()

    val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()


    fun fetchArticles() {
        articlesLiveData.postValue(articlesRepository.fetchArticles())
    }

}